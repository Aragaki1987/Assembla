yes | rm -r ./build/
mkdir build

yes | rm -r ./src/
mkdir src

wsimport -d ./build -b ./web/WEB-INF/wsdl/binding.xml -s ./src -wsdllocation ./src/com/stub/wsdl ./web/WEB-INF/wsdl/Calculator.wsdl

