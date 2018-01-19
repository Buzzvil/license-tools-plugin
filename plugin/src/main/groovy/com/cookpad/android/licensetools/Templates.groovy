package com.cookpad.android.licensetools

import groovy.text.SimpleTemplateEngine
import groovy.transform.CompileStatic

import java.util.zip.ZipFile

@CompileStatic
public class Templates {

    static final SimpleTemplateEngine templateEngine = new SimpleTemplateEngine()

    static String buildLicenseHtml(LibraryInfo library) {
        def templateFile = "template/licenses/${library.normalizedLicense}.html"
        return templateEngine.createTemplate(readResourceContent(templateFile)).make([
                "library": library
        ])
    }

    public static String buildLibraryHtml(LibraryInfo library) {
        assertLibraryHasEnoughInformation(library)

        def templateFile = "template/library.html"
        return templateEngine.createTemplate(readResourceContent(templateFile)).make([
                "name": library.name,
                "license": makeIndent(buildLicenseHtml(library), 4)
        ])
    }

    public static void assertLibraryHasEnoughInformation(LibraryInfo library) {
        if (!library.name) {
            throw new NotEnoughInformationException(library)
        }
        if (!library.license) {
            throw new NotEnoughInformationException(library)
        }
    }

    public static String wrapWithLayout(CharSequence content) {
        def templateFile = "template/layout.html"
        return templateEngine.createTemplate(readResourceContent(templateFile)).make([
                "content": makeIndent(content, 4)
        ])
    }

    static String makeIndent(CharSequence content, int level) {
        def s = new StringBuilder()
        content.eachLine { line ->
            for (int i = 0; i < level; i++) {
                s.append(" ")
            }
            s.append(line)
            s.append("\n")
        }
        return s.toString()
    }

    static String readResourceContent(String filename) {
        def templateFileUrl = Templates.class.getClassLoader().getResource(filename)
        if (templateFileUrl == null) {
            throw new FileNotFoundException("File not found: $filename")
        }
        templateFileUrl = new URL(templateFileUrl.toString())

        try {
            return templateFileUrl.openStream().getText("UTF-8")
        } catch (FileNotFoundException e) {
            // fallback to read JAR directly
            URI jarFile = (templateFileUrl.openConnection() as JarURLConnection).jarFileURL.toURI()
            ZipFile zip
            try {
                zip = new ZipFile(new File(jarFile))
            } catch (FileNotFoundException ex) {
                System.err.println("[plugin] no plugin.jar. run `./gradlew plugin:jar` first.")
                throw ex
            }
            return zip.getInputStream((zip.getEntry(filename))).getText("UTF-8")
        }
    }
}
