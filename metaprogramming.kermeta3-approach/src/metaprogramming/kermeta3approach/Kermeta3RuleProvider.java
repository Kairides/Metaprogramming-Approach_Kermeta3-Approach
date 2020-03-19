package metaprogramming.kermeta3approach;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import metaprogramming.ecoreapproach.EcoreRuleProvider;
import metaprogramming.extensionpoint.IRule;
import metaprogramming.extensionpoint.IRuleProvider;

public class Kermeta3RuleProvider implements IRuleProvider {
	
	private Set<IRule> ruleSet = new HashSet<IRule>();

	public Kermeta3RuleProvider() {
		ruleSet.addAll(new EcoreRuleProvider().getValidationRules());
	}
	
	public Collection<IRule> getValidationRules(){
		return ruleSet;
		
	}

}
