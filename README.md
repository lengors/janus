# Welcome to Janus &middot; [![GitHub license](https://img.shields.io/github/license/lengors/janus?color=blue)](https://github.com/lengors/janus/blob/main/LICENSE) [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=lengors_janus&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=lengors_janus)

Welcome to **janus**, a lightweight backend service providing supporting endpoints for user and directory operations. Built with **Spring Boot**, and **virtual threads**, Janus is designed to be highly scalable and efficient.

## Getting Started

#### Clone the repository

```bash
git clone https://github.com/lengors/janus.git
cd janus
```

#### Build the project

Ensure you have Gradle and JDK installed. Run:

```bash
./gradlew clean build
```

#### Run tests

```bash
./gradlew clean test
```

### Build & Deployment

- **Docker Support**: Includes a `Dockerfile` to deploy the service as a container. Modify it as needed for your deployment scenario.
- **CI/CD Pipelines**: Fully automated pipelines for code quality checks, build, testing, publishing, and deployment.

## Documentation and Resources

For detailed guides and additional information, please refer to
our [GitHub Wiki](https://github.com/lengors/janus/wiki).

If you wish to check an example of the generated API documentation, visit
the [Dokka generated reference](https://lengors.github.io/janus) page.

## Contributing

Contributions are welcome! Please refer to our [Contribution Guidelines](./CONTRIBUTING.md) for more information on how
to get involved.

## License

This project is licensed under [The Unlicense](./LICENSE), which places it in the public domain.