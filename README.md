# Wordagam

<p align="center">
<a href="https://opensource.org/licenses/MIT"><img src="https://img.shields.io/badge/License-MIT-yellow.svg" alt="License: MIT" /></a>
<a href="https://github.com/gravetii/wordagam/releases/latest"><img src="https://img.shields.io/github/release/gravetii/wordagam.svg?style=flat-square" alt="GitHub release" /></a>
<a href="https://github.com/gravetii/wordagam/releases"><img src="https://img.shields.io/github/downloads/gravetii/wordagam/total.svg?style=flat-square" alt="Github All Releases" /></a>
<a href="https://saythanks.io/to/gravetii"><img src="https://img.shields.io/badge/Say%20Thanks-!-1EAEDB.svg" alt="Say Thanks!" /></a>
</p>

A fun & interactive word game. Check out the screenshots and the gameplay video.

## Download & Play

Download the latest artefacts [here](https://github.com/gravetii/wordagam/releases/tag/v3.0). You can download the MacOS artifact [here](https://github.com/gravetii/wordagam/releases/download/v3.0/wordagam-mac.zip), but artefacts for other platforms can also be built as easily. Check the `Building from source` section below.

After downloading the zip file, extract it and run `wordagam` -  

```
./wordagam-mac/bin/wordagam
```

You can download an older version of the game as a JAR file [here](https://github.com/gravetii/wordagam/releases/download/v1.6/wordagam-1.6.jar).

## Prerequisites

The latest version of the artifact doesn't require a pre-existing Java runtime. This is possible because the project makes use of `jlink` to create a custom runtime image which comes bundled with the Java runtime.

## Building from source

The project is built as a modular Java application using gradle. You can build the project and create a custom runtime image for your platform using `jlink`. Run this in the root folder of the project - 
```
./gradlew clean jlink
```

Running this command creates the runtime image which you can run to play the game - 
```
build/image/bin/wordagam
```

## Game play video

<a href="http://www.youtube.com/watch?feature=player_embedded&v=EuWyjQGn0H0
"><img src="http://img.youtube.com/vi/EuWyjQGn0H0/2.jpg" alt="Wordagam game play video" width="240" height="180" /></a>

## Screenshots

![Screenshot 1](screenshots/1.jpg)
![Screenshot 2](screenshots/2.jpg)
![Screenshot 3](screenshots/3.jpg)
![Screenshot 4](screenshots/4.jpg)
![Screenshot 5](screenshots/5.jpg)
![Screenshot 6](screenshots/6.jpg)
![Screenshot 7](screenshots/7.jpg)
![Screenshot 8](screenshots/8.jpg)
![Screenshot 9](screenshots/9.jpg)
![Screenshot 10](screenshots/10.jpg)
![Screenshot 11](screenshots/11.jpg)
![Screenshot 12](screenshots/12.jpg)

---

<div>Theme images picked from <a href="https://unsplash.com" target="_blank">Unsplash</a>.</div>
