# qrcode
qrcode is simple tool for qrcode .

## demo
```java
// encode
QrCodeUtils.encodeQRCode("hello", "./test.jpg");
// decode
QrCodeUtils.decodeQRCode("./test.jpg"));
```

## package
`mvn clean package -Dmaven.test.skip=true
`