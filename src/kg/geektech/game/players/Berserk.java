package kg.geektech.game.players;

public class Berserk extends Hero {
    private int savedDamage;

    public Berserk(int health, int damage, String name) {
        super(health, damage, name, SuperAbility.SAVE_DAMAGE_AND_REVERT);
    }

    public int getSavedDamage() {
        return savedDamage;
    }

    public void setSavedDamage(int savedDamage) {
        this.savedDamage = savedDamage;
    }

    @Override
    public void applySuperPower(Boss boss, Hero[] heroes) {
        hit(boss);
    }

    public void hit(Boss boss) {
        if ( this.getHealth() > 0 && boss.getHealth() > 0){
            boss.setHealth(boss.getHealth() - this.getSavedDamage());
            System.out.println("Азиз сделал двойной удар! ");
        }
    }
}
