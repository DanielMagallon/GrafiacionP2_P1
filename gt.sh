#!/bin/bash

echo "# GrafiacionP2_P1" >> README.md
git init
git add README.md
git commit -m "first commit"
git branch -M main
git remote add origin https://github.com/DanielMagallon/GrafiacionP2_P1.git
git push -u origin main
