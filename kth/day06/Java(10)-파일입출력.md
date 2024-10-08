# Java(10) 파일 입출력

# File 클래스

### 특징

- 파일 및 디렉토리를 객체로서 관리
- 특정 파일 또는 디렉토리에 대응되는 객체를 생성
- 파일 및 디렉토리에 대한 정보를 관리하고 조작할 수 있는 다양한 메서드를 제공
- 파일 객체의 생성: 경로를 문자열로 전달, 해당 파일 또는 디렉토리 경로에 대응되는 객체 생성
    - File file = new File(”파일 또는 디렉토리의 경로”)
- 문자열 경로는 상대 경로 또는 절대 경로를 사용
- 경로가 절대 경로인지 아닌지는 isAbsolute() 메서드로 확인
- 상대 경로
    - 현재 프로그램이 실행되는 위치가 기준
        - STS를 사용할 때는 현재 프로젝트 폴더 기준
        - 경로가 C:\나 /로 시작하지 않음
- 절대 경로
    - 윈도우의 경우 드라이브 문자(C:\), 유닉스 계열의 경우 /로 시작하면 절대경로

### 주요 메서드

```java
import java.io.File;

public class FileTest {
	public static void main(String[] args) {
		// File : 파일 & 디렉토리 관리하기 위한 클래스
		File f =  new File("dog.jpg"); // 상대경로를 쓰고있다.
		System.out.println(f.exists()); // true
		System.out.println(f.isFile()); // true
		System.out.println(f.isAbsolute()); // false
		System.out.println(f.getAbsolutePath()); // /Users/kimpepe/Downloads/live-java/수업_후_코드/Java_Day10/src/dog.jpg
		System.out.println(f.length() + " bytes."); // 816902 bytes
		
		File src = new File("src");
		System.out.println(src.exists()); // true
		System.out.println(src.isDirectory()); // true
		
		File folder = new File("folder");
		System.out.println(folder.exists()); // false
		folder.mkdir(); // -> 폴더가 생성됨.
	}
}

```

# 입출력과 스트림

### 입출력

- 데이터의 입력(Input)  / 출력(Output)
- 컴퓨터 내부 혹은 외부의 장치와 데이터를 주고 받는 행위

### 스트림

- 데이터를 운반하는데 사용되는 통로
- 물의 흐름과 같이 단방향으로만 통신 가능
- 하나의 스트림을 이용하여 입력과 출력 처리 불가능
- 입력 스트림, 출력 스트림

### 노드

- 데이터의 출발 지점 혹은 목적지
- 키보드, 모니터, 파일, 자바 프로그램

## 데이터 타입에 따른 스트림 분류

- 입출력 스트림
    - 바이트 스트림
        - 입력 바이트 스트림 (…InputStream)
        - 출력 바이트 스트림 (…OutputStream)
    - 문자 스트림
        - 입력 문자 스트림 (…Reader)
        - 출력 문자 스트림 (…Writer)

### 스트림의 종류

- 데이터의 타입
    - 바이트
    - 문자
- 데이터의 방향
    - 입력
    - 출력
- 노드의 종류
    - 표준 입출력
    - 파일
- 스트림의 성격
    - 노드 스트림
    - 보조 스트림

# 바이트 스트림

### 특징

- 바이트 단위로 데이터를 입력 혹은 출력하기 위한 스트림 클래스
- 주로 이진 파일(이미지, 오디오, 비디오, …)를 읽고 쓰는 데 사용
- 최상위 추상 클래스: InputStream, OutputStream
- 노드의 종류에 따라 다양하고 구체적인 서브 클래스 사용ㄹ

## InputStream 클래스

| 메서드 | 설명 |
| --- | --- |
| `int read() throws IOException` | - 입력 스트림으로부터 한 바이트의 데이터를 읽음
- 읽은 바이트는 0에서 255 사이의 값을 가지며, 스트림의 끝에 도달(더 이상 읽을 값이 없음)하면 -1을 반환 |
| `int read(byte[] b) throws IOException` | - 입력 스트림으로부터 데이터를 읽어 주어진 바이트 배열 b를 채움
- 최대 b.length 갯수만큼의 바이트를 읽음
- 실제로 읽은 바이트의 개수를 반환하며, 스트림의 끝에 도달하면 -1을 반환 |
| `int read(byte[] b, int off, int len) throws IOException` | - 입력 스트림으로부터 주어진 바이트 배열 b의 특정 오프셋 offset부터 최대 len 길이만큼 바이트를 읽음
- 바이트를 읽어서 바이트 배열의 offset부터 시작하여 바이트를 채워나가고 읽은 바이트 개수를 return
- 반환 값: 실제로 읽은 바이트 개수 또는 스트림의 끝에 도달했을 때 -1 |
| `void close() throws IOException` | - 입력 스트림을 닫고 모든 시스템 자원을 해제
- 반환 값: 없음
- 스트림을 닫는 동안 입력/출력 오류가 발생하면 `IOException` 예외를 발생시킴 |

## OutputStream 클래스

| 메서드 | 설명 |
| --- | --- |
| `int read() throws IOException` | - 입력 스트림으로부터 한 바이트의 데이터를 읽음
- 읽은 바이트는 0에서 255 사이의 값을 가지며, 스트림의 끝에 도달(더 이상 읽을 값이 없음)하면 -1을 반환
- byte 하나를 읽어서 int형으로 반환
- 반환값: 읽은 바이트(0-255) 또는 스트림의 끝에 도달했을 때 -1
- 입출력 과정에 오류가 발생하면 IOException 예외를 발생시킴 |
| `int read(byte[] b) throws IOException` | - 입력 스트림으로부터 데이터를 읽어서 주어진 바이트 배열 b를 채움
- 최대 b.length 개수만큼의 바이트를 읽음
- 실제로 읽은 바이트의 개수를 반환하며, 스트림의 끝에 도달하면 -1을 반환
- 반환값: 읽은 바이트 개수 또는 스트림의 끝에 도달했을 때 -1 |
| `int read(byte[] b, int off, int len) throws IOException` | - 입력 스트림으로부터 주어진 바이트 배열 b의 특정 오프셋 off부터 최대 len 길이만큼 바이트를 읽음
- 바이트를 읽어서 바이트 배열b의 offset부터 시작하여 바이트를 채워넣가고 읽은 바이트 개수를 return
- 반환 값 : 실제로 읽은 바이트 개수 또는 스트림의 끝에 도달했을 때 -1 |
| `void close() throws IOException` | - 입력 스트림을 닫고 모든 시스템 자원을 해제
- 반환 값: 없음
- 스트림을 닫는 동안 입출력 오류가 발생하면 IOException 예외를 발생시킴 |

### 바이트 스트림 활용 예시

```java
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteStreamTest1 {
	public static void main(String[] args) {
		// byte stream을 이용해서 이미지를 입출력해보자!
		// stream => close() => finally
		
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		try {
			fis = new FileInputStream("dog.jpg");
			fos = new FileOutputStream("dog-copy.jpg");
			
			int b; // byte 바구니
			
			while((b = fis.read()) != -1) { // 하나의 바이트를 읽어와서, 있다면
				fos.write(b); // 하나의 바이트를 출력스트림에 쓴다.
			}
			System.out.println("복사 끝.");
		} catch (IOException e) {

		} finally {
			
			try {
				if(fis !=null)
					fis.close(); // null.close(); 
				if(fos !=null)
					fos.close();
				
			} catch (IOException e) {

				e.printStackTrace();
			}
			
		}
		
	}
}

```

```java
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteStreamTest2 {
	public static void main(String[] args) {
		// try with resources
		
		try(FileInputStream fis = new FileInputStream("dog.jpg");
			FileOutputStream fos = new FileOutputStream("dog-copy-2.jpg");
			){
			
			int b;
			while((b = fis.read()) != -1) {
				fos.write(b);
			}
			System.out.println("끝.");
			
		} catch(IOException e) {
			
		}
		System.out.println("스트림은 알아서 닫혔습니다.");
		
	}
}

```

```java
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteStreamTest3 {
	public static void main(String[] args) {
		// try with resources
		
		try(FileInputStream fis = new FileInputStream("dog.jpg");
			FileOutputStream fos = new FileOutputStream("dog-copy-3.jpg");
			){
			
			int b; // 버퍼에 쓴 바이트의 개수!!!
			// 버퍼를 이용해서 read()
			byte[] buffer = new byte[10];
			
			while((b = fis.read(buffer)) != -1) { // 입력스트림으로부터 버퍼에 바이트를 채운다음
				// 채운 바이트의 수를 반환.
//				System.out.println(b);
				fos.write(buffer, 0, b);
			}
			System.out.println("끝.");
			
		} catch(IOException e) {
			
		}
		System.out.println("스트림은 알아서 닫혔습니다.");
		
	}
}

```

# 문자 스트림 Character Stream

### 특징

- 바이트 스트림과는 달리 문자 단위(16비트 유니코드) 단위로 데이터를 처리
- 자바는 내부적으로 UTF-16 인코딩을 사용하여 문자를 메모리에 저장
- 주로 키보드에서 입력을 받거나 텍스트 파일을 읽고 쓰는 데 사용
- 문자 스트림의 최상위 추상 클래스: Reader, Writer
- FileReader와 FileWriter가 파일에 텍스트를 읽거나 쓸 때는 현재 JVM이 돌아가고 있는 시스템의 기본 문자 인코딩 방식을 사용
- 기본 문자 인코딩 확인
    - System.getProperty(:”file.encoding”);

## Reader  클래스

| 메서드 | 설명 |
| --- | --- |
| `int read() throws IOException` | - 입력 스트림에서 문자 하나를 읽음
- 읽은 문자는 0에서 65,535 범위의 정수 값으로 반환
- 스트림의 끝에 도달하여 더 이상 읽을 값이 없으면 -1을 반환 |
| `int read(char[] cbuf) throws IOException` | - 입력 스트림에서 문자를 읽어 문자 배열 cbuf를 채움
- 실제로 읽은 문자 개수를 반환하거나 스트림의 끝에 도달하면 -1을 반환 |
| `int read(char[] cbuf, int off, int len) throws IOException` | - 입력 스트림에서 최대 len개의 문자를 읽어 문자 배열 cbuf의 지정된 오프셋 위치 off부터 저장
- 실제로 읽은 문자 개수를 반환하거나 스트림의 끝에 도달하면 -1을 반환 |
| `void close() throws IOException` | 입력 스트림을 닫고 모든 시스템 자원을 반환 |

### Writer 클래스

| 메서드 | 설명 |
| --- | --- |
| `void write(int c) throws IOException` | - 단일 문자를 출력 스트림에 씀
- 입력 파라미터 c는 0에서 65,535 범위의 정수 값 |
| `void write(char[] cbuf) throws IOException` | - 주어진 문자 배열 cbuf에 저장된 문자를 출력 스트림에 씀 |
| `void write(char[] cbuf, int off, int len) throws IOException` | - 주어진 문자 배열 cbuf에서 지정된 오프셋 off부터 len 길이만큼의 문자를 출력 스트림에 씀 |
| `void write(String str) throws IOException` | - 주어진 문자열 str의 모든 문자를 출력 스트림에 씀 |
| `void write(String str, int off, int len) throws IOException` | - 주어진 문자열 str의 지정된 오프셋 off부터 len 길이만큼의 문자를 출력 스트림에 씀 |
| `void flush() throws IOException` | - 버퍼가 있는 스트림에서 버퍼링된 모든 문자를 출력하고 버퍼를 비움 |
| `abstract public void close()` | - 스트림을 종료해서 자원을 반환<br>내부적으로 `flush()`를 호출 |

# 스트림의 성격에 따른 분류

## 노드 스트림 Node Stream

### 특징

- 실제 노드에 연결
- 데이터의 실제 입출력을 담당

## 보조 스트림 Filter Stream

### 특징

- 실제 노드에 연결되지 않음
- 다른 스트림을 감싸서 추가적인 기능(ex: 버퍼링, 데이터 형식 변환, 객체 직렬화)을 제공
- 여러 보조 스트림을 체인 형태로 연결하여 다양한 기능을 조합할 수 있음
- 노드 스트림 없이 단독으로 사용할 수 `없`음
- 보조 스트림의 close()를 호출하면 노드 스트림의 close()까지 호출됨

### 종류

| 입력 | 출력 | 설명 |
| --- | --- | --- |
| FilterInputStream | FilterOutputStream | - 바이트 보조 스트림의 기본 클래스
- 하위 클래스: BufferedInputStream, DataInputStream 등 |
| BufferedInputStream | BufferedOutputStream | - 버퍼를 이용한 입출력 성능향상 |
| BufferedReader | BufferedWriter |  |
| ObjectInputStream | ObjectOutputStream | - 데이터를 객체 단위로 읽고 쓰는 스트림
- 객체를 직렬화된 형식으로 읽고 씀 |
| DataInputStream | DataOutputStream | - int, float과 같은 기본형 데이터를 읽고 쓰는 스트림 |
| InputStreamReader | OutputStreamWriter | - 바이트 스트림을 문자 스트림으로 변환 |

---

# 직렬화 Serialization

### 특징

- 객체의 상태를 바이트 스트림으로 변환하여 저장하거나 전송할 수 있ㄷ로고 하는 과정
- 역직렬화 (Deserialization): 직렬화된 바이트 스트림을 다시 객체로 복원하는 절차
- ObjectOutputStream: 객체를 바이트 스트림으로 변환하는 스트림
- ObjectInputStream: 바이트 스트림을 객체로 복원하는 스트림

### 직렬화 가능 클래스 만들기

- 객체가 ObjectOutputStream에 의해 직렬화하기 위해서는 해당 객체의 클래스가 반드시 Serializable 인터페이스를 구현해야 함
- Serializable 인터페이스: 메서드를 포함하지 않으며, 직렬화 가능 여부를 표시하는 마커 인터페이스
- 해당 인터페이스를 구현한 클래스를 상속받았다면 구현하지 않아도 된다
- 자손에만 구현했다면 조상 클래스는 직렬화 되지 않는다
- 직렬화 과정에서 제외하고 싶은 필드는 transient 키워들르 통해 직렬화 대상에서 제외시킬 수 있음
- serialVersionUID: 클래스의 버전 관리를 위해 serialVersionUID를 명시적으로 선언하는 것을 권장
    - 클래스의 변경으로 인한 역직렬화 오류를 방지하는 데 사용