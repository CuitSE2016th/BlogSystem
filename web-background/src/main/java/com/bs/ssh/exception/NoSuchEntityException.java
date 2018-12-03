package com.bs.ssh.exception;

/**
 * 实体不存在异常
 *
 * @author Egan
 * @date 2018/12/3 20:43
 **/
public class NoSuchEntityException extends RuntimeException{

   public NoSuchEntityException(String msg){
       super(msg);
   }
}
