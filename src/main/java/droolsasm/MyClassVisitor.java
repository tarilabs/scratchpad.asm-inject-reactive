package droolsasm;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.drools.core.phreak.ReactiveObject;
import org.mvel2.asm.AnnotationVisitor;
import org.mvel2.asm.ClassVisitor;
import org.mvel2.asm.Label;
import org.mvel2.asm.MethodVisitor;
import org.mvel2.asm.Opcodes;
import org.mvel2.asm.Type;
import org.mvel2.asm.tree.FieldNode;

import adding.MarkerInterface;

public class MyClassVisitor extends ClassVisitor implements Opcodes {
	private MethodVisitor mv;
	private String className;
	
	public MyClassVisitor(ClassVisitor cv) {
		super(ASM5, cv);
		this.cv = cv;
		
		System.out.println(Type.getDescriptor(ReactiveObject.class));
		System.out.println(Type.getInternalName(ReactiveObject.class));
		
	}
	
	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
		className = name;
		System.out.println(name);
		Set<String> original = new HashSet<String>();
		original.addAll(Arrays.asList(interfaces));
		original.add(Type.getInternalName(ReactiveObject.class));
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
		// add fields and new method at the end.
		FieldNode fn = new FieldNode(Opcodes.ACC_PRIVATE, "$$_drools_lts", "Ljava/util/List;", "Ljava/util/List<Lorg/drools/core/spi/Tuple;>;", null);
		fn.accept(cv);
		DOaddLeftTuple();
		DOgetLeftTuples();
		
		// final management
		className = null;
		
		// done.
		super.visitEnd();
	}
	
	private void DOgetLeftTuples() {
		mv = cv.visitMethod(ACC_PUBLIC, "getLeftTuples", "()Ljava/util/List;", "()Ljava/util/List<Lorg/drools/core/spi/Tuple;>;", null);
		mv.visitCode();
		Label l0 = new Label();
		mv.visitLabel(l0);
		mv.visitLineNumber(44, l0);
		mv.visitVarInsn(ALOAD, 0);
		mv.visitFieldInsn(GETFIELD, className, "$$_drools_lts", "Ljava/util/List;");
		mv.visitInsn(ARETURN);
		Label l1 = new Label();
		mv.visitLabel(l1);
		mv.visitLocalVariable("this", "L"+className+";", null, l0, l1, 0);
		mv.visitMaxs(1, 1);
		mv.visitEnd();
		}
	
	private void DOaddLeftTuple() {
		mv = cv.visitMethod(ACC_PUBLIC, "addLeftTuple", "(Lorg/drools/core/spi/Tuple;)V", null, null);
		mv.visitCode();
		Label l0 = new Label();
		mv.visitLabel(l0);
		mv.visitLineNumber(37, l0);
		mv.visitVarInsn(ALOAD, 0);
		mv.visitFieldInsn(GETFIELD, className, "$$_drools_lts", "Ljava/util/List;");
		Label l1 = new Label();
		mv.visitJumpInsn(IFNONNULL, l1);
		Label l2 = new Label();
		mv.visitLabel(l2);
		mv.visitLineNumber(38, l2);
		mv.visitVarInsn(ALOAD, 0);
		mv.visitTypeInsn(NEW, "java/util/ArrayList");
		mv.visitInsn(DUP);
		mv.visitMethodInsn(INVOKESPECIAL, "java/util/ArrayList", "<init>", "()V", false);
		mv.visitFieldInsn(PUTFIELD, className, "$$_drools_lts", "Ljava/util/List;");
		mv.visitLabel(l1);
		mv.visitLineNumber(40, l1);
		mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
		mv.visitVarInsn(ALOAD, 0);
		mv.visitFieldInsn(GETFIELD, className, "$$_drools_lts", "Ljava/util/List;");
		mv.visitVarInsn(ALOAD, 1);
		mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z", true);
		mv.visitInsn(POP);
		Label l3 = new Label();
		mv.visitLabel(l3);
		mv.visitLineNumber(41, l3);
		mv.visitInsn(RETURN);
		Label l4 = new Label();
		mv.visitLabel(l4);
		mv.visitLocalVariable("this", "L"+className+";", null, l0, l4, 0);
		mv.visitLocalVariable("leftTuple", "Lorg/drools/core/spi/Tuple;", null, l0, l4, 1);
		mv.visitMaxs(3, 2);
		mv.visitEnd();
		}

}