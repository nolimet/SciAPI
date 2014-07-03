package sciapi.api.mc.data;

import java.util.*;

import net.minecraft.nbt.*;
import sciapi.api.abstraction.absutil.IExUtil;
import sciapi.api.basis.data.*;
import sciapi.api.def.data.*;

public class NBTioUtil implements INBTio, IExUtil<NBTioUtil, IDataMap> {
	
	IDataMap map;

	@Override
	public NBTioUtil set(IDataMap val) {
		map = val;
		return this;
	}

	@Override
	public IDataMap get() {
		return map;
	}
	
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		for(Object key : nbt.func_150296_c()){
			String skey = (String) key;
			NBTBase nbtb = (NBTBase) nbt.getTag(skey);
			StringTag tag = new StringTag(skey);
			
			Object o = NBTtoObj(nbtb);
			if(o != null)
				map.set(tag, o);
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		for(IDataTag tag : map.getAllTag()){
			Object o = map.get(tag);
			String name = tag.toString();
			
			NBTBase nbtb = ObjtoNBT(o);
			if(nbtb != null)
				nbt.setTag(name, nbtb);
		}
	}
	
	
	private Object NBTtoObj(NBTBase nbtb){
		if(nbtb instanceof NBTTagByte)
			return ((NBTTagByte) nbtb).func_150290_f();
		else if(nbtb instanceof NBTTagByteArray)
			return ((NBTTagByteArray) nbtb).func_150292_c();
		else if(nbtb instanceof NBTTagDouble)
			return ((NBTTagDouble) nbtb).func_150286_g();
		else if(nbtb instanceof NBTTagFloat)
			return ((NBTTagFloat) nbtb).func_150288_h();
		else if(nbtb instanceof NBTTagInt)
			return ((NBTTagInt) nbtb).func_150287_d();
		else if(nbtb instanceof NBTTagIntArray)
			return ((NBTTagIntArray) nbtb).func_150302_c();
		else if(nbtb instanceof NBTTagLong)
			return ((NBTTagLong) nbtb).func_150291_c();
		else if(nbtb instanceof NBTTagShort)
			return ((NBTTagShort) nbtb).func_150289_e();
		else if(nbtb instanceof NBTTagString)
			return ((NBTTagString) nbtb).func_150285_a_();
		/*else if(nbtb instanceof NBTTagList){
			List list = new ArrayList();
			TagObject to;
			for(int i = 0; i < ((NBTTagList) nbtb).tagCount(); i++){
				NBTBase child = ((NBTTagList) nbtb).;
				to = new TagObject(new StringTag(child.getName()), NBTtoObj(child));
				list.add(to);
			}
			
			return list;
		}*/
		else if(nbtb instanceof NBTTagCompound){
			DataSet sub = new DataSet();
			IDataMap pmap = map;
			this.set(sub);
			this.readFromNBT((NBTTagCompound) nbtb);
			this.set(pmap);
			
			return sub;
		}
		
		return null;
	}
	
	private NBTBase ObjtoNBT(Object o){
		NBTBase ret = null;
		
		if(o instanceof Byte){
			ret = new NBTTagByte((Byte) o);
		}
		else if(o instanceof byte[]){
			ret = new NBTTagByteArray((byte[]) o);
		}
		else if(o instanceof Double){
			ret = new NBTTagDouble((Double) o);
		}
		else if(o instanceof Float){
			ret = new NBTTagFloat((Float) o);
		}
		else if(o instanceof Integer){
			ret = new NBTTagInt((Integer) o);
		}
		else if(o instanceof int[]){
			ret = new NBTTagIntArray((int[]) o);
		}
		else if(o instanceof Long){
			ret = new NBTTagLong((Long) o);
		}
		else if(o instanceof Short){
			ret = new NBTTagShort((Short) o);
		}
		else if(o instanceof String){
			ret = new NBTTagString((String) o);
		}
		/*else if(o instanceof List){
			ret = new NBTTagList(tag);
			TagObject obj;
			
			for(Object ob : ((List)o)){
				obj = (TagObject) ob;
				((NBTTagList) ret).appendTag(ObjtoNBT(obj.tag.toString(), obj.obj));
			}
		}*/
		else if(o instanceof IDataMap){
			ret = new NBTTagCompound();
			IDataMap sub = (IDataMap) o;
			IDataMap pmap = map;
			this.set(sub);
			this.writeToNBT((NBTTagCompound)ret);
			this.set(pmap);
		}
		
		return ret;
	}
}
