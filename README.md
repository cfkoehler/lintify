[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?style=for-the-badge&logo=gitpod&logoColor=white)](https://gitpod.io/#https://github.com/cfkoehler/lintify)
[![CodeQL](https://github.com/cfkoehler/lintify/actions/workflows/codeql.yml/badge.svg?branch=main)](https://github.com/cfkoehler/lintify/actions/workflows/codeql.yml)
[![Java CI with Maven](https://github.com/cfkoehler/lintify/actions/workflows/maven.yml/badge.svg?branch=main)](https://github.com/cfkoehler/lintify/actions/workflows/maven.yml)
# lintify
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

