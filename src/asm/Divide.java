package asm;
import org.objectweb.asm.*;
import util.Utilities.*;

public class Divide {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
		cw.visit(Opcodes.V11, Opcodes.ACC_PUBLIC, "Divide", null, "java/lang/Object", null);
		
		//Constructor
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
			mv.visitLdcInsn((Double)123.4); //Declares Double
			mv.visitVarInsn(Opcodes.DSTORE, 1); //Stores value in index 1 of stack
			mv.visitLdcInsn((Double)23.4); //Declares 2nd double
			mv.visitVarInsn(Opcodes.DSTORE, 3); //Stores value in index 3 of stack
			mv.visitVarInsn(Opcodes.DLOAD, 1); //loads first double
			mv.visitVarInsn(Opcodes.DLOAD, 3);//loads second double
			mv.visitInsn(Opcodes.DDIV); //divides doubles
			mv.visitVarInsn(Opcodes.DSTORE, 5); //stores divided value into index 5
			mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream");
			mv.visitVarInsn(Opcodes.DLOAD, 5); //loads divided value
			
		      mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(D)V", false); //prints value
	            
	            mv.visitInsn(Opcodes.RETURN);
	            mv.visitMaxs(0,0);
	            mv.visitEnd();
			
		}//end method visitor (Double)
		
		
		//INTEGER
		{
			MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "main", "[Ljava/lang/String;)V", null, null);
			mv.visitCode();
			mv.visitLdcInsn((Integer)123); //Declares Integer
			mv.visitVarInsn(Opcodes.ISTORE, 1); //Stores in index 1 of stack
			mv.visitLdcInsn((Integer)23); //Declares 2nd integer
			mv.visitVarInsn(Opcodes.ISTORE, 2); //stores in index 2 of stack
			mv.visitVarInsn(Opcodes.ILOAD, 1);
			mv.visitVarInsn(Opcodes.ILOAD, 2); //^^ loads 1st and 2nd integers
			mv.visitInsn(Opcodes.IDIV); //divides values
			mv.visitVarInsn(Opcodes.ISTORE, 3); //stores answer
			mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream");
			mv.visitVarInsn(Opcodes.ILOAD, 3); //loads answer
			
		      mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(D)V", false); //prints answer
	             
	            mv.visitInsn(Opcodes.RETURN);
	            mv.visitMaxs(0,0);
	            mv.visitEnd();
		}//end method visitor (Integer)
		
		
		//LONG (same instructions as int and double)
		{
			MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "main", "[Ljava/lang/String;)V", null, null);
			mv.visitCode();
			mv.visitLdcInsn((Long)12345L); //declares long
			mv.visitVarInsn(Opcodes.LSTORE, 1); //stores in index 1
			mv.visitLdcInsn((Long)2345L); //repeats process for 2nd long
			mv.visitVarInsn(Opcodes.LSTORE, 3);
			mv.visitVarInsn(Opcodes.LLOAD, 1); 
			mv.visitVarInsn(Opcodes.LLOAD, 3); //loads both longs
			mv.visitInsn(Opcodes.LDIV); //divides
			mv.visitVarInsn(Opcodes.LSTORE, 5); //stores answer
			mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream");
			mv.visitVarInsn(Opcodes.LLOAD, 5); //loads answer
			
		      mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(J)V", false); //prints answer
	            
	            mv.visitInsn(Opcodes.RETURN);
	            mv.visitMaxs(0,0);
	            mv.visitEnd();
		}//end method visitor (Long)
		
		//write method visitors to byte
	       cw.visitEnd();

	        byte[] b = cw.toByteArray();

	        util.Utilities.writeFile(b,"Divide.class");
	        
	        System.out.println("Done!");
		
	}//end main

}//end Divide.java
