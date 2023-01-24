package me.connortech.lintify.rules;

public class rule {
    private String ruleName;

    public rule(String name) {
        System.out.println(String.format("Constructing Rule: %s", name));
        this. ruleName = name;
    }

    public String getRuleName() {
        return ruleName;
    }
}
