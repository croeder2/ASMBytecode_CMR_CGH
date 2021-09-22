package asm;
import org.objectweb.asm.*;
import util.Utilities.*;

public class Compare implements Opcodes {

	public static void main(String[] args) {
		
		//Classwriter
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
		cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC,"Compare", null, "java/lang/Object",null);
		
		{
			//Method Visitor constructor
			MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
			mv.visitCode();
			mv.visitVarInsn(Opcodes.ALOAD, 0); //load the first local variable (this)
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V",false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(1,1);
			mv.visitEnd();
			
		}
		
		{
			//Declaring labels for jumps
			Label firstIsGreater = new Label();
			Label secondIsGreater = new Label();
			Label end = new Label();
			
			MethodVisitor mv=cw.visitMethod(Opcodes.ACC_PUBLIC+Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
			mv.visitCode();
			
			//Declare and load integers that will be compared
			mv.visitLdcInsn((Integer)10);
			mv.visitVarInsn(Opcodes.ISTORE,1);
			mv.visitLdcInsn((Integer)12);
			mv.visitVarInsn(Opcodes.ISTORE,2);
			mv.visitVarInsn(Opcodes.ILOAD,1);
			mv.visitVarInsn(Opcodes.ILOAD,2);
			mv.visitJumpInsn(Opcodes.IF_ICMPLE, firstIsGreater);  
			
				mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");//If jump statement does not execute print out first number
				mv.visitInsn(Opcodes.ICONST_1);
				mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
				mv.visitJumpInsn(Opcodes.GOTO, end);//go to the end 
			
			
			mv.visitLabel(firstIsGreater);
			mv.visitVarInsn(Opcodes.ILOAD,1);
	        mv.visitVarInsn(Opcodes.ILOAD,2);
	        mv.visitJumpInsn(Opcodes.IF_ICMPNE, secondIsGreater);
	        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");//print zero since numbers are equal
	        mv.visitInsn(Opcodes.ICONST_0);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
            mv.visitJumpInsn(Opcodes.GOTO, end);//go to the end
            
            	mv.visitLabel(secondIsGreater);
            	mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");//-1 is printed since the second number is greater
            	mv.visitInsn(Opcodes.ICONST_M1);
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
	        
			mv.visitLabel(end);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0,0);
			mv.visitEnd();
			
			//Declare and load longs that will be compared
			mv.visitLdcInsn((long)18);
            mv.visitVarInsn(Opcodes.LSTORE,3);
            mv.visitLdcInsn((long)9);
            mv.visitVarInsn(Opcodes.LSTORE,5);
            mv.visitVarInsn(Opcodes.LLOAD,3);
            mv.visitVarInsn(Opcodes.LLOAD,5);
            mv.visitInsn(Opcodes.LCMP);//pops the two most recently added numbers off the stack to check if one is greater than the other
            mv.visitVarInsn(Opcodes.ISTORE,7);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");//print the result
            mv.visitVarInsn(Opcodes.ILOAD, 7);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
            
            //Declare and load doubles that will be compared
            mv.visitLdcInsn((Double)27.8);
            mv.visitVarInsn(Opcodes.DSTORE,9);
            mv.visitLdcInsn((Double)21.1);
            mv.visitVarInsn(Opcodes.DSTORE,11);
            mv.visitVarInsn(Opcodes.DLOAD,9);
            mv.visitVarInsn(Opcodes.DLOAD,11);
            mv.visitInsn(Opcodes.DCMPG);//pops the two most recently added numbers off the stack to check if one is greater than the other
            mv.visitVarInsn(Opcodes.ISTORE,13);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");//print  result
            mv.visitVarInsn(Opcodes.ILOAD, 13);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
			
		}
		
			cw.visitEnd();
			byte[] b = cw.toByteArray();

	        util.Utilities.writeFile(b,"Compare.class");
	        
	        System.out.println("Done!");
		
	}
	
}