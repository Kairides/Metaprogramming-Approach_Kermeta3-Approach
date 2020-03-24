package rules;

import java.util.ArrayList;
import org.eclipse.core.resources.*;
import org.eclipse.jdt.core.*;
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
		if("k3".matches(entry.getKey())) {
			String aspectsFields = entry.getValue();
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			
			IJavaProject jProj = null;
			
			for(IProject proj : root.getProjects()) {
				if(proj.getName().endsWith("dsa")) {
					jProj = JavaCore.create(proj);
				}
			}
			if(jProj == null) {
				return (new Message("No project dsa in the workspace", Severity.ERROR));
			}
			
			ArrayList<String> aspectsName = new ArrayList<>();
			ArrayList<String> aspectsAnnotation = new ArrayList<>();
			
			for(String s :aspectsFields.split(", ")) {
				aspectsName.add(s);
			}
			
			for(String asp : aspectsName) {
				System.out.println("aspect "+ asp);
				try {
					IType type = jProj.findType(asp);
					System.out.println(type.getKey());
					for(IMethod meth : type.getMethods()) {
						System.out.println(meth.getSignature().toString());
						for(IAnnotation annot : meth.getAnnotations()) {
							aspectsAnnotation.add(annot.getElementName());
						}
					}
				} catch (JavaModelException e) {
					return (new Message("No aspect matching "+asp+ " in the k3dsa project", Severity.ERROR));
				}
			}
			
			if(!aspectsAnnotation.contains("Main")) {
				return (new Message("No method annotated with \"Main\" in the k3dsa project", Severity.ERROR));
			}
			
			if(!aspectsAnnotation.contains("InitializeModel")) {
				return (new Message("No method annotated with \"InitializeModel\" in the k3dsa project", Severity.ERROR));
			}
			
		}
		return (new Message("", Severity.DEFAULT));
	}

}
