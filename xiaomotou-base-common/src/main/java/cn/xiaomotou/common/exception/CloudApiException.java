package cn.xiaomotou.common.exception;

public class CloudApiException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CloudApiException(String message){
		super(message);
	}
	
	public CloudApiException(Throwable cause)
	{
		super(cause);
	}
	
	public CloudApiException(String message, Throwable cause)
	{
		super(message,cause);
	}
}
