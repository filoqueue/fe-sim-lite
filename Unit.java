public class Unit{
  
  private String name;
  private FEClass _class;
  private Weapon weapon;
  private int health; // maximum # of hit points
  private int hp; // current # of hit points
  private int str; // Strength: for calculating physical attack power
  private int mag; // Magic: for calculating magical attack power
  private int skl; // Skill: for calculating accuracy and critical rate
  private int spd; // Speed: for calculating avoidance and determining who(if anyone) attacks twice
  private int lck; // Luck: for calculating critical avoidance and a few other things
  private int def; // Defense: mitigates incoming physical damage
  private int res; // Resistance: mitigates incoming magical damage
  private int atk; // Attack: The damage a unit will deal
  private int as; // Attack Speed: Speed after weapon weight penalties (if any)
  private int hit; // Hit: How accurate this unit's attacks will be
  private int crit; // Critical Rate: The probability of doing triple damage
  private int avo; // Avoidance: The probability of evading an attack
  private int ddg; // dodge AKA critical evasion: mitigates the enemy's critical rate
  
  public Unit(){
    
    name = "Monster";
    _class = new FEClass();
    weapon = new Weapon();
    health = 30;
    hp = 30;
    str = 15;
    mag = 15;
    skl = 15;
    spd = 15;
    lck = 15;
    def = 15;
    res = 15;
    atk = str + weapon.getMt();
    if((str - weapon.getWt()) <=0)
      as = spd;
    else
      as = spd - (str - weapon.getWt());
    hit = (skl * 2) + lck + weapon.getHit() + _class.getHit();
    avo = (as * 2) + lck + _class.getAvo();
    crit = (skl / 2) + weapon.getCrit() + _class.getCrit();
    ddg = lck + _class.getDdg();
    
  } // DC
  
  public Unit(String name, Weapon weapon, FEClass _class, int health, int hp, int str, int mag, int skl, int spd, int lck, int def, int res){
    
    this.name = name;
    this._class = _class;
    this.weapon = weapon;
    this.health = health;
    this.hp = hp;
    this.str = str;
    this.mag = mag;
    this.skl = skl;
    this.spd = spd;
    this.lck = lck;
    this.def = def;
    this.res = res;
    atk = str + weapon.getMt();
    if((str - weapon.getWt()) <=0) 
      as = spd;
    else
      as = spd - (str - weapon.getWt());
    hit = (skl * 2) + lck + weapon.getHit() + _class.getHit();
    avo = (as * 2) + lck + _class.getAvo();
    crit = (skl / 2) + weapon.getCrit() + _class.getCrit();
    ddg = lck + _class.getDdg();    
    
  } // 11C
//*********************************************************************************************************************
  public String getName(){return this.name;}
  public FEClass getFEClass(){return this._class;}
  public Weapon getWeapon(){return this.weapon;}
  public int getHealth(){return this.health;}
  public int getHP(){return this.hp;}
  public int getStr(){return this.str;}
  public int getMag(){return this.mag;}
  public int getSkl(){return this.skl;}
  public int getSpd(){return this.spd;}
  public int getLck(){return this.lck;}
  public int getDef(){return this.def;}
  public int getRes(){return this.res;}
  public int getAtk(){return this.atk;}
  public int getAS(){return this.as;}
  public int getHit(){return this.hit;}
  public int getAvo(){return this.avo;}
  public int getCrit(){return this.crit;}
  public int getDdg(){return this.ddg;}
  
  public void setHP(int hp){this.hp = hp;}  // more like public Boyd setHP amirite?? make sure to call this mutator right after combat  
//*********************************************************************************************************************
  public String toString(){ // this will be the default toString. another one can be made for a more comprehensive view
    
    String s = this.name + "  HP: " + hp + "/" + health + "\nATK: " + this.atk + "  HIT: " + this.hit + "  AVO: " + this.avo + "\nCRIT: " + this.crit + "  DDG: " + this.ddg + "\n";
    return s;
    
  } // toString
  
  
  
} // class