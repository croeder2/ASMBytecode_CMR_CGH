package asm;
import org.objectweb.asm.*;
import util.Utilities.*;

//Generates a random number (1 + (int)(Math.random() * ((5 - 1) + 1))). Prints the number if it is greater than 2 and prints hello if it is less than 2

public class Logic implements Opcodes {
    
    public static void main(String[] args){
        
    	//Class writer
        ClassWriter cw = new ClassWriter(0);
        cw.visit(52, ACC_PUBLIC + ACC_SUPER, "Logic", null, "java/lang/Object", null);
        
        {
        	//Method Visitor constructor
            MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            mv.visitInsn(RETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
        {   
        	Label lessThan = new Label();
        	Label greaterThan = new Label();
        	
            MethodVisitor mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
            mv.visitCode();
            mv.visitInsn(ICONST_1); //Pushes integer with value of 1
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Math", "random", "()D", false); //Create random number generator
            mv.visitLdcInsn((Double)4.0); //Declares double with value of 5.0
            mv.visitInsn(DMUL); //Multiplies random number by 5
            mv.visitInsn(D2I); //Convert double result to integer
            mv.visitInsn(IADD); //Adds integer with value of 1
            mv.visitVarInsn(ISTORE, 1); //Store result
            mv.visitVarInsn(ILOAD, 1); //Load result for comparison
            mv.visitInsn(ICONST_2); //Pushes integer with value of 2
            mv.visitJumpInsn(IF_ICMPLE, lessThan); //If result is less than 2 for to block of code that prints Hello
            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;"); 
            mv.visitVarInsn(ILOAD, 1); //Load result for printing
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
            mv.visitJumpInsn(GOTO, greaterThan);
            
            //Prints hello
            mv.visitLabel(lessThan);
            mv.visitFrame(Opcodes.F_APPEND,1, new Object[] {Opcodes.INTEGER}, 0, null);
            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("Hello");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            
            mv.visitLabel(greaterThan);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitInsn(RETURN);
            mv.visitMaxs(5, 2);
            mv.visitEnd();
        }
        cw.visitEnd();

        byte[] b = cw.toByteArray();

        util.Utilities.writeFile(b,"Logic.class");
        
        System.out.println("Done!");
    }
}