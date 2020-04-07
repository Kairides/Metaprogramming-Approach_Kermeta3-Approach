package metaprogramming.kermeta3approach;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.gemoc.dsl.approach.IRule;
import org.eclipse.gemoc.dsl.approach.IRuleProvider;

import metaprogramming.ecoreapproach.EcoreRuleProvider;
import rules.*;

public class Kermeta3RuleProvider implements IRuleProvider {
	
	private Set<IRule> ruleSet = new HashSet<IRule>();

	public Kermeta3RuleProvider() {
		ruleSet.addAll(new EcoreRuleProvider().getValidationRules());
		ruleSet.add(new Kermeta3Rule());
	}
	
	public Collection<IRule> getValidationRules(){
		return ruleSet;
		
	}

}
