# Список терминов семинара
###### Нужно написать определения с примером из жизни или кода
- @Bean - говорим spring явно создать и вернуть объект (@Component - сканирует и сам решает что делать)
- @Configuration - говорим spring, что в этом классе нужно искать методы
- Jacoco что это и как включить - инструмент для анализа покрытия кода тестами, включить - добавить в build.gradle.kts
```shell
plugins {
	...
	jacoco
}
tasks.test {
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}
tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
}
```
- Checkstyle что это и как включить - инструмент для анализа стиля написания кода
```shell
plugins {
	...
	checkstyle
}
checkstyle {
	toolVersion = "10.13.0"
	isIgnoreFailures = false
	maxWarnings = 0
	maxErrors = 0
}
```
- application.yml - файл, лежащий в main/resources, в котором можно задавать параметры нашего файла
- при запуске run with Coverage сразу же покажется покрытия теста, без перехода на сайт (я честно не знал об этом)