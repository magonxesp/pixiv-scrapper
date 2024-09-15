export function replaceInFile (file, searchRegex, newString) {
    const content = Deno.readTextFileSync(file)
    const newContent = content.replaceAll(searchRegex, newString)
    Deno.writeTextFileSync(file, newContent)
}
