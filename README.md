[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?style=for-the-badge&logo=gitpod&logoColor=white)](https://gitpod.io/#https://github.com/cfkoehler/lintify)
[![CodeQL](https://github.com/cfkoehler/lintify/actions/workflows/codeql.yml/badge.svg?branch=main)](https://github.com/cfkoehler/lintify/actions/workflows/codeql.yml)
[![Java CI with Maven](https://github.com/cfkoehler/lintify/actions/workflows/maven.yml/badge.svg?branch=main)](https://github.com/cfkoehler/lintify/actions/workflows/maven.yml) <br>

| [![SonarCloud](https://sonarcloud.io/images/project_badges/sonarcloud-orange.svg)](https://sonarcloud.io/summary/new_code?id=cfkoehler_lintify)                                                    |
|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=cfkoehler_lintify&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=cfkoehler_lintify)              |
| [![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=cfkoehler_lintify&metric=sqale_index)](https://sonarcloud.io/summary/new_code?id=cfkoehler_lintify)                    |
| [![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=cfkoehler_lintify&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=cfkoehler_lintify)         |
| [![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=cfkoehler_lintify&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=cfkoehler_lintify) |
| [![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=cfkoehler_lintify&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=cfkoehler_lintify)               |
| [![Bugs](https://sonarcloud.io/api/project_badges/measure?project=cfkoehler_lintify&metric=bugs)](https://sonarcloud.io/summary/new_code?id=cfkoehler_lintify)                                     |
| [![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=cfkoehler_lintify&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=cfkoehler_lintify)               |
| [![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=cfkoehler_lintify&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=cfkoehler_lintify)           |
| [![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=cfkoehler_lintify&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=cfkoehler_lintify)                       |
| [![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=cfkoehler_lintify&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=cfkoehler_lintify)                           |
| [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=cfkoehler_lintify&metric=coverage)](https://sonarcloud.io/summary/new_code?id=cfkoehler_lintify)                             |  


 lintify
Java Framework for flat text file linting

## Goals
The goals of customLint is to provide a software solution for linting custom formated and generated configuration files using a rule driven approach. A yaml file will be provided to the program that defines the rule's and actions that need to be taken based on the outcome.

## Initial Rule Ideas
- Each line starts with some specific regex
- Another rule is that each line in the file has to be unique
- Another rule is that the start of each line needs to be unique
- Max amount of characters in a line

# Build
To build and get usable artifact run `mvn clean verify -Pdist`
Then run sh `target/lintify/bin/lintify`

