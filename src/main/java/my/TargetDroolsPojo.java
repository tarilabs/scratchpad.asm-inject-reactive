package my;

import java.util.ArrayList;
import java.util.List;

import org.drools.core.phreak.ReactiveObject;
import org.drools.core.phreak.ReactiveObjectUtil;
import org.drools.core.spi.Tuple;

public class TargetDroolsPojo implements ReactiveObject {
	private String name;
	private int number;
	
	public TargetDroolsPojo(String name, int number) {
		super();
		this.name = name;
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		ReactiveObjectUtil.notifyModification(this);
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
		ReactiveObjectUtil.notifyModification(this);
	};
	
	private List<Tuple> $$_drools_lts;

    public void addLeftTuple(Tuple leftTuple) {
        if ($$_drools_lts == null) {
            $$_drools_lts = new ArrayList<Tuple>();
        }
        $$_drools_lts.add(leftTuple);
    }

    public List<Tuple> getLeftTuples() {
        return $$_drools_lts;
    }

}
