class KtlintPlugin : org.gradle.api.Plugin<org.gradle.api.Project> {
    override fun apply(target: org.gradle.api.Project) {
        try {
            Class
                .forName("Ktlint_gradle")
                .getDeclaredConstructor(
                    org.gradle.api.Project::class.java,
                    org.gradle.api.Project::class.java,
                )
                .newInstance(target, target)
        } catch (exception: java.lang.reflect.InvocationTargetException) {
            throw exception.targetException
        }
    }
}
