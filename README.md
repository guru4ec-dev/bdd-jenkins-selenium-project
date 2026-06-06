# BDD Jenkins Selenium Project

> **Local and Jenkins-based UI functional test automation with BDD, Allure, and Cucumber reporting.**
> Built by [Guruvaiya Muthukaruppan](https://linkedin.com/in/guruvaiya-m) — Automation Architect with 19+ years delivering QA frameworks for Fortune 500 firms.

![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square)
![Selenium](https://img.shields.io/badge/Selenium-4-green?style=flat-square)
![Cucumber](https://img.shields.io/badge/Cucumber-BDD-brightgreen?style=flat-square)
![Jenkins](https://img.shields.io/badge/CI-Jenkins-red?style=flat-square)
![Allure](https://img.shields.io/badge/Reports-Allure-yellow?style=flat-square)

---

## What this framework demonstrates

A traditional, installation-based Selenium automation framework integrated with Jenkins CI/CD — the setup most commonly found in enterprise environments today.

Unlike containerised approaches, this framework reflects real-world constraints where:

- Teams run tests on dedicated Jenkins agents with pre-installed browsers
- Allure and Cucumber reports are published directly from the Jenkins pipeline
- Setup scripts (`setup.sh` / `setup.bat`) automate environment preparation on any machine
- No Docker or Grid required — straightforward, portable across Jenkins agents and local machines

---

## Tech stack

| Layer | Technology | Why |
|---|---|---|
| Browser automation | Java + Selenium WebDriver 4 | Industry standard; WebDriverManager handles driver binaries |
| BDD scenarios | Cucumber (BDD) | Business-readable test scenarios |
| Execution control | TestNG | Suite management and parallel configuration |
| CI/CD pipeline | Jenkins + Jenkinsfile | Pipeline-as-code; runs on push or scheduled trigger |
| Reporting | Allure + Cucumber HTML | Rich visual reports with step-level detail |
| Build tool | Maven | Dependency management and test execution |

---

## How it differs from the portable Grid framework

| Feature | This repo | [Portable Grid Framework](https://github.com/guru4ec-dev/portable-selenium-grid-framework) |
|---|---|---|
| Local setup required | Yes — browser + Java + Maven | No — Docker only |
| Grid / parallel browsers | No | Yes — Chrome + Firefox simultaneously |
| CI/CD | Jenkins (Jenkinsfile) | GitHub Actions |
| Reporting | Allure + Cucumber HTML | Masterthought HTML |
| Best for | Jenkins-based enterprise teams | Zero-install cloud CI environments |

---

## Project structure

```
├── src/
│   └── test/
│       ├── java/com/automation/
│       │   ├── pages/          ← Page Object Model classes
│       │   ├── steps/          ← Cucumber BDD step definitions
│       │   ├── hooks/          ← Before/After hooks + screenshot capture
│       │   ├── runners/        ← TestNG + Cucumber runner configuration
│       │   └── utils/          ← Utilities — WebDriverManager, config reader
│       └── resources/
│           ├── features/       ← Cucumber BDD feature files
│           └── testdata/       ← Test data files
├── jenkins-selenium-project/   ← Jenkins job configuration
├── allure-results/             ← Allure raw output (auto-generated)
├── Jenkinsfile                 ← Pipeline-as-code definition
├── testng.xml                  ← TestNG suite configuration
├── pom.xml                     ← Maven dependencies and plugin config
├── setup.sh                    ← Automated environment setup (Linux/Mac)
└── setup.bat                   ← Automated environment setup (Windows)
```

---

## Setup and running tests

### Prerequisites

- Java 17+
- Maven 3.8+
- Chrome or Firefox browser installed
- Jenkins (for CI/CD pipeline execution)

### Automated setup

```bash
# Linux / Mac
chmod +x setup.sh && ./setup.sh

# Windows
setup.bat
```

### Run tests locally

```bash
mvn clean verify
```

### Run via Jenkins

1. Create a new Jenkins Pipeline job
2. Point it to this repository
3. Jenkins will auto-detect the `Jenkinsfile` and execute the pipeline
4. Allure and Cucumber HTML reports are published as build artifacts

---

## CI/CD pipeline (Jenkinsfile)

The `Jenkinsfile` defines the full pipeline-as-code:

```
Checkout → Build → Run Tests → Publish Allure Report → Publish Cucumber Report
```

- Allure results auto-published to Jenkins Allure plugin
- Cucumber HTML report available as a build artifact on every run
- Pipeline triggers on every commit or on a scheduled basis

---

## Key engineering decisions

**WebDriverManager** — eliminates manual ChromeDriver/GeckoDriver version management; automatically downloads the correct driver binary at runtime.

**Page Object Model** — UI interactions encapsulated in page classes; step definitions stay clean and readable; locator changes require updates in one place only.

**Dual reporting (Allure + Cucumber HTML)** — Allure provides step-level visual breakdown with screenshots; Cucumber HTML gives business-readable scenario pass/fail summary. Both published automatically from Jenkins.

**Setup scripts (`setup.sh` / `setup.bat`)** — one-command environment preparation for new team members on both Linux/Mac and Windows; reduces onboarding friction on Jenkins agent machines.

**Jenkinsfile (pipeline-as-code)** — pipeline definition lives in the repo, not in Jenkins UI; version-controlled, reviewable, and portable across Jenkins instances.

---

## About the author

**Guruvaiya Muthukaruppan** — Automation Architect and Senior SDET with 19+ years building enterprise QA frameworks across Insurance, Banking, Retail, and Telecom.

Previously delivered automation at scale for Allianz, Home Depot (Fortune 500), Commonwealth Bank of Australia, and Vodafone. Co-developed the RAFT, TAM, and eRAFT frameworks adopted globally by JPMorgan Chase, Bank of America, and Humana at the TCS Centre of Excellence.

- 🔗 [LinkedIn](https://linkedin.com/in/guruvaiya-m)
- 💻 [GitHub](https://github.com/guru4ec-dev)
- 📧 guru4ec@gmail.com
