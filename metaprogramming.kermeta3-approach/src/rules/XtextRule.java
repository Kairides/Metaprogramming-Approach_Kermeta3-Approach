package rules;

import java.util.ArrayList;

import org.eclipse.gemoc.dsl.Dsl;
import org.eclipse.gemoc.dsl.Entry;

import metaprogramming.extensionpoint.IRule;
import metaprogramming.extensionpoint.Message;
import metaprogramming.extensionpoint.Severity;

public class XtextRule implements IRule{

	@Override
	public Message execute(Dsl dsl) {
		
		ArrayList<String> entriesNames = new ArrayList<String>();
		
		for (Entry e : dsl.getEntries()) {
			entriesNames.add(e.getKey());
		}
		
		if(!entriesNames.contains("xtext")) {
			return (new Message("Missing entry \"xtext\"", Severity.WARNING));
		}
			
		return (new Message("",Severity.DEFAULT));
	}

	@Override
	public Message execute(Entry entry) {
		// TODO Auto-generated method stub
		return null;
	}

}
