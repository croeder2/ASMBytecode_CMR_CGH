package asm;

import org.objectweb.asm.*;
import util.Utilities.*; //import Utilities.java from the util package

public class Multiply {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
		cw.visit(Opcodes.V11, Opcodes.ACC_PUBLIC, "Multiply", null, "java/lang/Object", null);
		
		{
			MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
			mv.visitCode();
			mv.visitVarInsn(Opcodes.ALOAD, 0); //load the first local variable
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(1, 1);
			mv.visitEnd();
		}//end method  visitor (constructor)
		
		//DOUBLE
		{
			MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "main", "[Ljava/lang/String;)V", null, null);
			mv.visitCode();
			mv.visitLdcInsn((Double)173.43); //Declares double
			mv.visitVarInsn(Opcodes.DSTORE,1); //stores in 1st index
			mv.visitLdcInsn((Double)45.46); //declares 2nd double
			mv.visitVarInsn(Opcodes.DSTORE, 3); //stores in 3rd index
			mv.visitVarInsn(Opcodes.DLOAD, 1); //loads first double
			mv.visitVarInsn(Opcodes.DLOAD, 3); //loads second double
			mv.visitInsn(Opcodes.DMUL); //multiplies index 1 and 3
			mv.visitVarInsn(Opcodes.DSTORE, 5); //stores answer in index 5
			mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
			mv.visitVarInsn(Opcodes.DLOAD, 5);
			
		      mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(D)V", false); //prints answer
	            
	            mv.visitInsn(Opcodes.RETURN);
	            mv.visitMaxs(0,0);
	            mv.visitEnd();
	        } //end method visitor (double)
		
		//INT -- SAME COMMENTS AS PREVIOUS METHOD VISITOR BUT WITH INTEGER
		{
			MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "main", "[Ljava/lang/String;)V", null, null);
			mv.visitCode();
			mv.visitLdcInsn((Integer)173);
			mv.visitVarInsn(Opcodes.ISTORE,1);
			mv.visitLdcInsn((Integer)45);
			mv.visitVarInsn(Opcodes.ISTORE, 2);
			mv.visitVarInsn(Opcodes.ILOAD, 1);
			mv.visitVarInsn(Opcodes.ILOAD, 2);
			mv.visitInsn(Opcodes.IMUL); //multiplies index 1 and 3
			mv.visitVarInsn(Opcodes.ISTORE, 3); //stores answer in index 5
			mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
			mv.visitVarInsn(Opcodes.ILOAD, 3);
		      mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(D)V", false);
	            
	            mv.visitInsn(Opcodes.RETURN);
	            mv.visitMaxs(0,0);
	            mv.visitEnd();
		} //end method vistor (Integer)

		//LONG -- SAME COMMENTS AS DOUBLE METHOD VISITOR BUT WITH LONG
		{
			MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "main", "[Ljava/lang/String;)V", null, null);
			mv.visitCode();
			mv.visitLdcInsn((Long)643345742L);
			mv.visitVarInsn(Opcodes.LSTORE,1);
			mv.visitLdcInsn((Long)576534211L);
			mv.visitVarInsn(Opcodes.LSTORE, 3);
			mv.visitVarInsn(Opcodes.LLOAD, 1);
			mv.visitVarInsn(Opcodes.LLOAD, 3);
			mv.visitInsn(Opcodes.LMUL); //multiplies index 1 and 3
			mv.visitVarInsn(Opcodes.LSTORE, 5); //stores answer in index 5
			mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
			mv.visitVarInsn(Opcodes.LLOAD, 5);
		      mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(J)V", false);
	            
	            mv.visitInsn(Opcodes.RETURN);
	            mv.visitMaxs(0,0);
	            mv.visitEnd();
		}
	        cw.visitEnd();

	        byte[] b = cw.toByteArray();

	        util.Utilities.writeFile(b,"Multiply.class");
	        
	        System.out.println("Done!");
			
		} //end main
	
	} //end Class

