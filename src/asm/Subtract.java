package asm;

import org.objectweb.asm.*;
import util.Utilities.*;


public class Subtract {

	public static void main(String[] args) {
	
		//CLASSWRITER
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
		cw.visit(Opcodes.V11, Opcodes.ACC_PUBLIC, "Subtract", null, "java/lang/Object", null);
		
		{
			MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
			mv.visitCode();
			mv.visitVarInsn(Opcodes.ALOAD, 0); //load the first local variable
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(1, 1);
			mv.visitEnd();
		}//end method  visitor (constructor)
		
		//Double
		{
			MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "main", "[Ljava/lang/String;)V", null, null);
			mv.visitCode();
			mv.visitLdcInsn((Double)123.4); //declares a double
			mv.visitVarInsn(Opcodes.DSTORE, 1);
			mv.visitLdcInsn((Double)23.4); //declares second double
			mv.visitVarInsn(Opcodes.DSTORE, 3);
			mv.visitVarInsn(Opcodes.DLOAD, 1); //loads first double
			mv.visitVarInsn(Opcodes.DLOAD, 3); //loads second double
			mv.visitInsn(Opcodes.DSUB); //subtracts values
			mv.visitVarInsn(Opcodes.DSTORE, 5); //stores answer
			mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream");
			mv.visitVarInsn(Opcodes.DLOAD, 5);
			
		      mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(D)V", false); //prints answer
	            
	            mv.visitInsn(Opcodes.RETURN);
	            mv.visitMaxs(0,0);
	            mv.visitEnd();
			
		}//end method visitor (Double)
		
		
		//INTEGER --- SAME COMMENTS AS PREVIOUS BUT WITH INT
		{
			MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "main", "[Ljava/lang/String;)V", null, null);
			mv.visitCode();
			mv.visitLdcInsn((Integer)123);
			mv.visitVarInsn(Opcodes.ISTORE, 1);
			mv.visitLdcInsn((Integer)23);
			mv.visitVarInsn(Opcodes.ISTORE, 2);
			mv.visitVarInsn(Opcodes.ILOAD, 1);
			mv.visitVarInsn(Opcodes.ILOAD, 2);
			mv.visitInsn(Opcodes.ISUB);
			mv.visitVarInsn(Opcodes.ISTORE, 3);
			mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream");
			mv.visitVarInsn(Opcodes.ILOAD, 3);
			
		      mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(D)V", false);
	            
	            mv.visitInsn(Opcodes.RETURN);
	            mv.visitMaxs(0,0);
	            mv.visitEnd();
		}//end method visitor (integer)
		
		//LONG -- SAME COMMENTS AS PREVIOUS TWO BUT WITH LONG
		{
			MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "main", "[Ljava/lang/String;)V", null, null);
			mv.visitCode();
			mv.visitLdcInsn((Long)12345L);
			mv.visitVarInsn(Opcodes.LSTORE, 1);
			mv.visitLdcInsn((Long)2345L);
			mv.visitVarInsn(Opcodes.LSTORE, 3);
			mv.visitVarInsn(Opcodes.LLOAD, 1);
			mv.visitVarInsn(Opcodes.LLOAD, 3);
			mv.visitInsn(Opcodes.LSUB);
			mv.visitVarInsn(Opcodes.LSTORE, 5);
			mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream");
			mv.visitVarInsn(Opcodes.LLOAD, 5);
			
		      mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(J)V", false);
	            
	            mv.visitInsn(Opcodes.RETURN);
	            mv.visitMaxs(0,0);
	            mv.visitEnd();
		}
		//
	       cw.visitEnd();

	        byte[] b = cw.toByteArray();

	        util.Utilities.writeFile(b,"Subtract.class");
	        
	        System.out.println("Done!");
			
	}//end main

}// end Subtract.java
