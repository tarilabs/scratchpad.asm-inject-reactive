package myasm;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.mvel2.asm.ClassVisitor;

public class InterfacesAdder  extends ClassAdapter {
	 private Set newInterfaces;
	 public InterfacesAdder(ClassVisitor cv,
	 Set newInterfaces) {
	 super(cv);
	 this.newInterfaces = newInterfaces;
	 }
	 public void visit(int version, int access,
	 String name, String signature,
	 String superName, String[] interfaces) {
	 Set ints = new HashSet(newInterfaces);
	 ints.addAll(Arrays.asList(interfaces));
	 cv.visit(version, access, name, signature,
	 superName, (String[]) ints.toArray());
	 }
	} 