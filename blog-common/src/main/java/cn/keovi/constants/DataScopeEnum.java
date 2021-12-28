package cn.keovi.constants;

/**
 * 数据权限类型
 * @ClassName: DataScopeEnum
 * @Description: TODO
 * @author: Jason
 * @date: 2018年9月12日 下午4:25:55
 */
public enum DataScopeEnum {
	ALL(1, "可看所有"),
	SUBORDINATE(2, "可看下属"),
	SELF(3, "仅看自己"),
	ORG(4, "按组织结构");

    private Integer type;
    private String desc;
	private DataScopeEnum(Integer type, String desc) {
		this.type = type;
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}


	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
    
    public static String getDesc(Integer type) {
    	if (type == null) {
    		return null;
    	}
    	for (DataScopeEnum e : DataScopeEnum.values()) {
    		if (e.type.equals(type)) {
    			return e.getDesc();
    		}
    	}
    	
    	return null;
    }
    

    
}
