package com.cookpad.android.licensetools

public class LicenseToolsExtension {

    public static String NAME = "licenseTools"

    public File licensesYaml = new File("3rd_party_licenses.yml")

    public File outputHtml = new File("3rd_party_licenses.html")

    public File outputJson = new File("3rd_party_licenses.json")

    public Set<String> ignoredGroups = new HashSet<>()

    public Set<String> ignoredProjects = new HashSet<>()
}
