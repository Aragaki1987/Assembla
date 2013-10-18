echo CLEAN PROJECT ...
yes | rm -r ./build/
mkdir build

yes | rm -r ./src/com/san/calculator

echo GENERATE CALCULATOR ARTIFACT ...
wsimport -d ./build -b ./web/WEB-INF/wsdl/binding.xml -s ./src -wsdllocation ./src/com/stub/wsdl ./web/WEB-INF/wsdl/Calculator.wsdl
