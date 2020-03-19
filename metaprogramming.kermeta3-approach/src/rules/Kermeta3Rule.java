package rules;

import java.util.ArrayList;

import org.eclipse.gemoc.dsl.Dsl;
import org.eclipse.gemoc.dsl.Entry;

import metaprogramming.extensionpoint.IRule;
import metaprogramming.extensionpoint.Message;
import metaprogramming.extensionpoint.Severity;

public class Kermeta3Rule implements IRule {

	@Override
	public Message execute(Dsl dsl) {
		ArrayList<String> entriesNames = new ArrayList<String>();
		
		for (Entry e : dsl.getEntries()) {
			entriesNames.add(e.getKey());
		}
		
		if(!entriesNames.contains("k3")) {
			return (new Message("Missing entry \"k3\"", Severity.ERROR));
		}
			
		return (new Message("",Severity.DEFAULT));
	}

	@Override
	public Message execute(Entry entry) {
		if(entry.getKey().equals("k3")) {
			
		}
		return (new Message("", Severity.DEFAULT));
	}

}
