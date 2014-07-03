package sciapi.api.def.data;

import java.util.*;

import sciapi.api.basis.data.*;

public class IntegratedDataFormer implements IPartDataFormer {
	
	List<IPartDataFormer> pformerlist = new ArrayList();
	
	public void addDataFormer(IPartDataFormer pformer){
		pformerlist.add(pformer);
	}

	@Override
	public TagObject formData(String[] str) {
		for(IPartDataFormer ite : pformerlist)
			if(ite.isFormable(str))
				return ite.formData(str);
		
		return null;
	}

	@Override
	public String[] toStrings(TagObject tobj) {
		for(IPartDataFormer ite : pformerlist)
			if(ite.isThisForm(tobj))
				return ite.toStrings(tobj);
		
		return null;
	}

	
	@Override
	public boolean isFormable(String[] str) {
		for(IPartDataFormer ite : pformerlist)
			if(ite.isFormable(str))
				return true;
		return false;
	}

	@Override
	public boolean isThisForm(TagObject tobj) {
		for(IPartDataFormer ite : pformerlist)
			if(ite.isThisForm(tobj))
				return true;
		return false;
	}

}
