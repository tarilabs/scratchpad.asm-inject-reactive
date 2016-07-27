package droolsasm;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.drools.core.phreak.ReactiveObject;
import org.mvel2.asm.AnnotationVisitor;
import org.mvel2.asm.ClassVisitor;
import org.mvel2.asm.MethodVisitor;
import org.mvel2.asm.Opcodes;

import adding.MarkerInterface;

public class MyClassVisitor extends ClassVisitor {
	private String className;
	
	public MyClassVisitor(ClassVisitor cv) {
		super(Opcodes.ASM5, cv);
		this.cv = cv;
	}
	
	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
		className = name;
		Set<String> original = new HashSet<String>();
		original.addAll(Arrays.asList(interfaces));
		original.add(ReactiveObject.class.getPackage().getName().replace(".", "/") 
						+ "/" 
						+ ReactiveObject.class.getSimpleName());
		String[] interfaces2 = original.toArray(new String[]{});
		super.visit(version, access, name, signature, superName, interfaces2);
	}

	@Override
	public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
		System.err.println(desc);
		// TODO add checking if annotated...
		return super.visitAnnotation(desc, visible);
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
		System.err.println(name);
		MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
	    if (mv == null) {
			return null;
	    } else {
	    	if (isASetter(name)) {
	    		return new MyMethodVisitor(mv);
	    	} else {
	    		return mv;
	    	}
	    }
	}
	
	private boolean isASetter(String name) {
		return name.startsWith("set");
	}

	@Override
	public void visitEnd() {
		className = null;
		super.visitEnd();
	}

	
}