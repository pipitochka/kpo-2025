#import "@preview/basic-resume:0.2.3": *

// Put your personal information here, replacing mine
#let name = "Акулов Артем"
//#let location = "Москва, Россия"
#let email = "aaakulov_3@edu.hse.ru"
#let github = "github.com/pipitochka"
//#let linkedin = "linkedin.com/in/stuxf"
#let phone = "+7 (982) 669 48 70"
#let personal-site = "t.me/abacaba01"

#show: resume.with(
  author: name,
  // All the lines below are optional. 
  // For example, if you want to to hide your phone number:
  // feel free to comment those lines out and they will not show.
  //location: location,
  email: email,
  github: github,
  //linkedin: linkedin,
  phone: phone,
  personal-site: personal-site,
  accent-color: "#26428b",
  font: "New Computer Modern",
  paper: "us-letter",
  author-position: left,
  personal-info-position: left,
)

/*
* Lines that start with == are formatted into section headings
* You can use the specific formatting functions if needed
* The following formatting functions are listed below
* #edu(dates: "", degree: "", gpa: "", institution: "", location: "")
* #work(company: "", dates: "", location: "", title: "")
* #project(dates: "", name: "", role: "", url: "")
* #extracurriculars(activity: "", dates: "")
* There are also the following generic functions that don't apply any formatting
* #generic-two-by-two(top-left: "", top-right: "", bottom-left: "", bottom-right: "")
* #generic-one-by-two(left: "", right: "")
*/
== Образование

#edu(
  institution: "Национальный исследовательский университет «Высшая школа экономики» ",
  location: "Москва, Россия",
  dates: dates-helper(start-date: "Aug 2022", end-date: "May 2027"),
  degree: "Бакалавр компьютерных наук",
)
- GPA 8\/10
- Актуальные курсы: конструирование программного обсепечения, Database, формальные языки и автоматы, основы операционных систем, cтруктуры данных, алгоритмы и алгоритмические языки, линейная алгебра, дискретная математика, математический анализ.

//== Work Experience

// #work(
//   title: "Subatomic Shepherd and Caffeine Connoisseur",
//   location: "Atomville, CA",
//   company: "Microscopic Circus, Schrodinger's University",
//   dates: dates-helper(start-date: "May 2024", end-date: "Present"),
// )
// - more bullet points go here

// ... more headers and stuff below

//== Work Experience


== Навыки
- Умение работать с Git.
- Языки: Си, C++, C\#, Java, Python
- Разработка игр: Unity
- Проектирование и архитектура ПО: SOLID, GRASP, Design Patterns

== Проекты
- Кросс-компилятор языка Си в ассемблер архитектуры RISK-V. #link("https://github.com/pipitochka/2CourseWork")[Ссылка]

- Библиотека для С++, позволяющая работать с числами с плавабщей точкой неограниченной длины. #link("github.com/pipitochka/2courseCLongArithmeric")[Ссылка]

- Библиотека для С++, реализующая базу данных, с возможностями, близкими к SQL. #link("https://github.com/pipitochka/2courseCdb")[Ссылка]

- Библиотека для Си, позволяющая решать квадратные уравнения при помощи функционала, реализованнного #linebreak() на NASM. #link("https://github.com/pipitochka/1courseProjectAssembly")[Ссылка]

