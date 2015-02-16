# IntrusionDetector
Program Structure : 
Files were divided into 3 sections:
1-	Domain Model : Including interfaces in com.powa.detector package.
2-	Implementation: Including implementation of domain model in com.powa.detector.impl package.
3-	Test: Two different complete test cases in com.powa.detector.test

How Log being stored:
1-	Ip string being converted to 32-bits Long for minimizing memory consumption.
2-	Only Last 5 failed login epochs stored in a ring buffer.
3-	A ConcurrentHashMap of (ip 32-bits long value as a key) and (ringbuffer of Epochs as a value) will be stored.


