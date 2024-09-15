import { exec } from 'https://deno.land/x/exec/mod.ts'
import { replaceInFile } from './functions.js'

const projectName = prompt('Project name (my-example-library): ')
const projectNameLabel = prompt('Project name label (My example library): ')
const projectDescription = prompt('Project description: ')

replaceInFile('settings.gradle.kts', /rootProject\.name = \"kotlin-library-template\"/, `rootProject.name = \"${projectName}\"`)
replaceInFile('build.gradle.kts', /name = \"Kotlin library template\"/, `name = \"${projectNameLabel}\"`)
replaceInFile('build.gradle.kts', /description = \"The template for make new kotlin libraries\"/, `description = \"${projectDescription}\"`)
replaceInFile('build.gradle.kts', /github.com\/magonxesp\/kotlin\-library/, `github.com\/magonxesp\/${projectName}`)
replaceInFile('build.gradle.kts', /kotlin-library/, `${projectName}`)
replaceInFile('README.md', /Kotlin Library template/, `${projectNameLabel}`)
replaceInFile('README.md', /TODO: library description/, `${projectDescription}`)
replaceInFile('README.md', /.*init\.js.*/, '')
replaceInFile('README.md', /<artifact-id>/, projectName)

Deno.remove('./scripts/init.js')

const modifiedFiles = [
    'scripts/init.js',
    'README.md',
    'build.gradle.kts',
    'settings.gradle.kts'
]

for (const modifiedFile of modifiedFiles) {
    await exec(`git add ${modifiedFile}`)
}

await exec('git commit -m "project setup"')

console.info(`🟢 The project ${projectNameLabel} is now initialized`)
console.info('👉️ Now you can do "git push" command')
