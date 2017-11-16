/* Gregory Isaac Hughes
 * Right now all this class has are the methods necessary to simulate a round of combat
 * Eventually this class will have a main method, code to set up a GUI,
 * lists of weapons, classes, enemy units, and playable units, 
 * ways to pick a unit from the list and combat enemies in the Arena until it's hp <= 0 or the user stops fighting,
 * and maybe more?
 */

//imports for GUI needed once a GUI type is decided on
public class ArenaSim{
  
  public int rollRN(){ // roll a random number
    
    return (int)(Math.random() * 100) + 1;
    
  }
  
  public boolean succeeds(int goal){ // if a hit or crit succeeds
    
    int attempt = rollRN(); // if this line is my only call to rollRN(), I'll delete rollRN() and replace this call with the code in rollRN()
    if(attempt < goal)
      return false;
    else
      return true;
    
  }
  
  public int[] combat(Unit attacker, Unit defender){
    
    int[] dmgSuffered = {0, 0}; 
   /* dmgSuffered[0] is how much to subtract from the attackers hp. Ditto dmgSuffered[1] for the defender
    * whenever combat is called, immediately after should be code that subtracts dmgSuffered[0] from the
    * attacker's health and ditto for dmgSuffered[1] and the defender and if either are negative, set to 0;
    */ 
    if(attacker.getWeapon().advantage(defender.getWeapon())){ // if the attacker's weapon beats the defender's
      
      dmgSuffered[0] += attack(attacker, defender, 15, 1);
      dmgSuffered[1] += attack(defender, attacker, -15, -1);
      if((attacker.getAS() - defender.getAS()) >= 4) // if the attacker doubles the defender
        dmgSuffered[0] += attack(attacker, defender, 15, 0);
      else if((attacker.getAS() - defender.getAS()) < 4) // if the defender doubles the attacker
        dmgSuffered[1] += attack(defender, attacker, -15, -1);
      
    }
    else if(attacker.getWeapon().disadvantage(defender.getWeapon())){ // if the defender's weapon beats the attacker's
      
      dmgSuffered[0] += attack(attacker, defender, -15, -1);
      dmgSuffered[1] += attack(defender, attacker, 15, 1);
      if((attacker.getAS() - defender.getAS()) >= 4)
        dmgSuffered[0] += attack(attacker, defender, -15, -1);
      else if((attacker.getAS() - defender.getAS()) > 4)
        dmgSuffered[1] += attack(defender, attacker, 15, 1);
      
    }
    else{ // neither weapon beats the other
      
      dmgSuffered[0] += attack(attacker, defender, 0, 0);
      dmgSuffered[1] += attack(defender, attacker, 0, 0);
      if((attacker.getAS() - defender.getAS()) >= 4)
        dmgSuffered[0] += attack(attacker, defender, 0, 0);
      else if((attacker.getAS() - defender.getAS()) > 4)
        dmgSuffered[1] += attack(defender, attacker, 0, 0);
      
    }
    return dmgSuffered;
    
  } // combat
  
  private int attack(Unit attacker, Unit defender, int dmgMod, int hitMod){
    
    int dmgDone = 0;
    int attackerHit = attacker.getHit() + hitMod;
    boolean hitHappens = succeeds(attackerHit - defender.getAvo());
    boolean critHappens = succeeds(attacker.getCrit() - defender.getDdg());
    if(hitHappens){
      
      if(!attacker.getWeapon().getIsMagic())
        dmgDone = (attacker.getAtk() - defender.getDef()) + dmgMod;
      else
        dmgDone = (attacker.getAtk() - defender.getRes()) + dmgMod;
      if(critHappens)
        dmgDone *= 3;
      
    } // if
    return dmgDone;
    
  } // attack
  
} // class