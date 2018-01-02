/* Gregory Isaac Hughes
 * Right now all this class has are the methods necessary to simulate a round of combat
 * Eventually this class will have a main method, code to set up a GUI,
 * lists of weapons, classes, enemy units, and playable units, 
 * ways to pick a unit from the list and combat enemies in the Arena until it's hp <= 0 or the user stops fighting,
 * and maybe more?
 */

//imports for GUI needed once a GUI type is decided on
import java.util.ArrayList;
  
public class ArenaSim{
  
/*-------------Inits explained below----------------*/
  private static ArrayList<FEClass> cls;
  private static ArrayList<Weapon> weps;
  private static ArrayList<Unit> units;
/*--------------------------------------------------*/
  
  public static void main(String [] args)
  {
    cls = new ArrayList<FEClass>();
    weps = new ArrayList<Weapon>();
    units = new ArrayList<Unit>();
    initClasses();
    initWeapons();
    initUnits();
    
    Unit left = new Unit(units.get(0));
    Unit right = new Unit(units.get(2));
    printUnit(left);
    printUnit(right);
    
    System.out.println("Begin Combat");
    System.out.println("------------------------------------");
    while(!left.isDead() && !right.isDead())
    {
      System.out.printf("%s %d   %s %d\n",left.getName(),left.getHP(),right.getName(),right.getHP());
      int[] damage = combat(left,right);
      //System.out.printf("%d   %d\n",damage[0],damage[1]); //prints damage output for debugging
      left.setHP(left.getHP() - damage[1]);
      right.setHP(right.getHP() - damage[0]);
      
      if(left.getHP() < 0) {left.setHP(0);System.out.println(left.getName() + " is defeated.");}
      if(right.getHP() < 0) {right.setHP(0);System.out.println(right.getName() + " is defeated.");}
      
    }
    System.out.printf("%s %d   %s %d\n",left.getName(),left.getHP(),right.getName(),right.getHP());
  }
  
  public static int rollRN(){ // roll a random number
    
    return (int)(Math.random() * 100) + 1;
    
  }
  
  public static boolean succeeds(int goal){ // if a hit or crit succeeds
    
    int attempt = rollRN(); // if this line is my only call to rollRN(), I'll delete rollRN() and replace this call with the code in rollRN()
    if(attempt > goal)
      return false;
    else
      return true;
    
  }
  
  public static int[] combat(Unit attacker, Unit defender){
    
    int[] dmgSuffered = {0, 0}; 
    /* dmgSuffered[0] is how much to subtract from the attackers hp. Ditto dmgSuffered[1] for the defender
     * whenever combat is called, immediately after should be code that subtracts dmgSuffered[0] from the
     * attacker's health and ditto for dmgSuffered[1] and the defender and if either are negative, set to 0;
     */ 
    if(attacker.getWeapon().advantage(defender.getWeapon())){ // if the attacker's weapon beats the defender's
      
      dmgSuffered[0] += attack(attacker, defender, 15, 1);
      if((defender.getHP() - dmgSuffered[0]) >= 0) //if the defender has HP remaining
      {
        dmgSuffered[1] += attack(defender, attacker, -15, -1);
        if((attacker.getAS() - defender.getAS()) >= 4) // if the attacker doubles the defender
          dmgSuffered[0] += attack(attacker, defender, 15, 0);
        else if((attacker.getAS() - defender.getAS()) <= -4) // if the defender doubles the attacker
          dmgSuffered[1] += attack(defender, attacker, -15, -1);
      }
      
    }
    else if(attacker.getWeapon().disadvantage(defender.getWeapon())){ // if the defender's weapon beats the attacker's
      
      dmgSuffered[0] += attack(attacker, defender, -15, -1);
      if((defender.getHP() - dmgSuffered[0]) >= 0) //if the defender has HP remaining
      {
        dmgSuffered[1] += attack(defender, attacker, 15, 1);
        if((attacker.getAS() - defender.getAS()) >= 4)
          dmgSuffered[0] += attack(attacker, defender, -15, -1);
        else if((attacker.getAS() - defender.getAS()) <= -4)
          dmgSuffered[1] += attack(defender, attacker, 15, 1);
      }
    }
    else{ // neither weapon beats the other
      
      dmgSuffered[0] += attack(attacker, defender, 0, 0);    //initial attack
      if((defender.getHP() - dmgSuffered[0]) >= 0) //if the defender has HP remaining
      {
        dmgSuffered[1] += attack(defender, attacker, 0, 0);    //initial counterattack
        if((attacker.getAS() - defender.getAS()) >= 4)         //if the attacker doubles       
          dmgSuffered[0] += attack(attacker, defender, 0, 0);   //second attack from attacker
        else if((attacker.getAS() - defender.getAS()) <= -4)   //if the defender doubles  
          dmgSuffered[1] += attack(defender, attacker, 0, 0);   //second attack from the defender 
      }
    }
    return dmgSuffered;
    
  } // combat
  
  private static int attack(Unit attacker, Unit defender, int hitMod, int dmgMod){
    
    System.out.println(attacker.getName() + " attacks " + defender.getName() + ".");
    int dmgDone = 0;
    int attackerHit = attacker.getHit() + hitMod;
    boolean hitHappens = succeeds(attackerHit - defender.getAvo());
    boolean critHappens = succeeds(attacker.getCrit() - defender.getDdg());
    if(hitHappens){
      
      if(!attacker.getWeapon().getIsMagic()){
        
        dmgDone = (attacker.getAtk() - defender.getDef()) + dmgMod;
        
      }
      else{
        
        dmgDone = (attacker.getAtk() - defender.getRes()) + dmgMod;
        
      }
      if(critHappens){
        
        dmgDone *= 3;
        System.out.println("Critical Hit!");
        
      }
      System.out.println(attacker.getName() + " dealt " + dmgDone + " damage.");
      
    } // if
    
    return dmgDone;
    
  } // attack
  
  private static void initClasses() // initializes some examples of classes to be used as a demo
  {
    cls.add(new FEClass("Myrmidon",0,0,5,0));
    cls.add(new FEClass("Swordmaster",0,10,15,0));
    cls.add(new FEClass("Soldier")); // there's a constructor that takes just a string and sets hit, avo, crit, and ddg to 0
    cls.add(new FEClass("Halberdier",0,0,5,10)); // also used for Spear Masters
    cls.add(new FEClass("Fighter"));
    cls.add(new FEClass("Warrior"));
    cls.add(new FEClass("Berserker",0,0,20,-5));
  }
  
  private static void initWeapons() // initializes some unit weapons
  {
    weps.add(new Weapon("Iron Sword",  'R', 'G', 'B', 5, 90, 0, 7, false));
    weps.add(new Weapon("Steel Sword", 'R', 'G', 'B', 8, 75, 0, 12, false));
    weps.add(new Weapon("Iron Lance",  'B', 'R', 'G', 7, 80, 0, 8, false));
    weps.add(new Weapon("Steel Lance", 'B', 'R', 'G', 10, 70, 0, 13, false));
    weps.add(new Weapon("Iron Axe",    'G', 'B', 'R', 8, 75, 0, 10, false));
    weps.add(new Weapon("Steel Axe",   'G', 'B', 'R', 11, 65, 0, 15, false));
    weps.add(new Weapon("Killing Edge", 'R', 'G', 'B', 9, 75,30, 9, false));
  }
  
  private static void initUnits() // creates some example units to be used for arena testing
  {
    /* For ease of testing, all units are assumed to be the male variation */
    
    //units.add(new Unit(String name, Weapon weapon, FEClass _class, int health, int hp, int str, int mag, int skl, int spd, int lck, int def, int res));
    units.add(new Unit("Myrmidon", weps.get(0), cls.get(0), 17, 17, 4, 0, 5, 5, 0, 3, 0));
    units.add(new Unit("Soldier", weps.get(2), cls.get(2), 20, 20, 4, 0, 4, 4, 0, 4, 0));
    units.add(new Unit("Fighter", weps.get(4), cls.get(4), 24, 24, 5, 0, 3, 3, 0, 3, 0));
  }
  
  private static void printUnit(Unit x)
  {
    Weapon wep = x.getWeapon();
    System.out.println("=============================");
    System.out.println(x.getFEClass().name);
    System.out.println("-----------------------------");
    System.out.println(x.getName());
    System.out.println("HP " + x.getHP() + "  Sp " + x.getSpd());
    System.out.println("Str " + x.getStr() + "  Lck " + x.getLck());
    System.out.println("Mag " + x.getMag() + "  Def " + x.getDef());
    System.out.println("Skl " + x.getSkl() + "  Res " + x.getRes());
    System.out.println("-----------------------------");
    System.out.printf("%s (%c)\n", wep.getName(),wep.getType());
    System.out.printf("Atk %d  Hit %d  Avo %d\n", x.getAtk(),x.getHit(),x.getAvo());
    System.out.printf("Crit %d  Ddg %d\n", x.getCrit(),x.getDdg());
    System.out.println("=============================");
  }
  
} // class