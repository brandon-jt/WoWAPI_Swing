import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpecHolder {
    // add a getter, so if input = Warlock, return WarlockSpecs
    // maybe make these private, then actually return like above with a function call
    // big switch statement to take in the initial input

    private static final String[] DeathKnightSpecs = {"Frost", "Unholy", "Blood"};
    private static final String[] DemonHunterSpecs = {"Havoc", "Vengeance"};
    private static final String[] DruidSpecs = {"Feral", "Balance", "Guardian", "Restoration"};
    private static final String[] HunterSpecs = {"Survival", "Beast Mastery", "Marksmanship"};
    private static final String[] MageSpecs = {"Arcane", "Fire", "Frost"};
    private static final String[] MonkSpecs = {"Windwalker", "Brewmaster", "Mistweaver"};
    private static final String[] PaladinSpecs = {"Retribution", "Holy", "Protection"};
    private static final String[] PriestSpecs = {"Holy", "Discipline", "Shadow"};
    private static final String[] RogueSpecs = {"Subtlety", "Outlaw", "Assassination"};
    private static final String[] ShamanSpecs = {"Elemental", "Enhancement", "Restoration"};
    private static final String[] WarlockSpecs = {"Affliction", "Destruction", "Demonology"};
    private static final String[] WarriorSpecs = {"Fury", "Arms", "Protection"};

    public static Map<String, String[]> ClassSpecializations = Map.ofEntries(
            new AbstractMap.SimpleEntry<>("Death Knight", DeathKnightSpecs),
            new AbstractMap.SimpleEntry<>("Demon Hunter", DemonHunterSpecs),
            new AbstractMap.SimpleEntry<>("Druid", DruidSpecs),
            new AbstractMap.SimpleEntry<>("Hunter", HunterSpecs),
            new AbstractMap.SimpleEntry<>("Mage", MageSpecs),
            new AbstractMap.SimpleEntry<>("Monk", MonkSpecs),
            new AbstractMap.SimpleEntry<>("Paladin", PaladinSpecs),
            new AbstractMap.SimpleEntry<>("Priest", PriestSpecs),
            new AbstractMap.SimpleEntry<>("Rogue", RogueSpecs),
            new AbstractMap.SimpleEntry<>("Shaman", ShamanSpecs),
            new AbstractMap.SimpleEntry<>("Warlock", WarlockSpecs),
            new AbstractMap.SimpleEntry<>("Warrior", WarriorSpecs));

    // Im assuming we'll have to do the above with also doing the above (again) inside on the right hand side, so this'll probably be pretty verbose
    // I might not have to do the same on the right hand side since each map will only have a few keys pairs

    // It might be better for me to create a class that we can just throw into a map, such as Map<String, Object>
    // We're going with one object to hold all the specs and their numbers, so we only have one map.
    // Then iterate over the object
    // maybe use an ENUM, such as FROST = 63
    // then pass in (Death Knight, Frost, 63, Unholy, 64, Blood, 65)
    // this.class = Death Knight
    // this.spec1 = Frost
    // this.spec1num = 63.
    // Then pass this into the map Object

    //


    public static String[] getSpecs(String ClassName){
        //System.out.println(ClassName);
        return ClassSpecializations.get(ClassName);
    }
}
