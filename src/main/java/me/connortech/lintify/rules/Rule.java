package me.connortech.lintify.rules;

import java.util.Map;

public class Rule {
    private String ruleName;
    private String ruleType;
    private Map<String, String> ruleParams;
    private boolean warnOnly;

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }
    public String getRuleType() {
        return ruleType;
    }
    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }
    public Map<String, String> getRuleParams() {
        return ruleParams;
    }
    public void setRuleParams(Map<String, String> ruleParams) {
        this.ruleParams = ruleParams;
    }
    public boolean isWarnOnly() {
        return warnOnly;
    }
    public void setWarnOnly(boolean warnOnly) {
        this.warnOnly = warnOnly;
    }
    public String getRuleName() {
        return ruleName;
    }
}
