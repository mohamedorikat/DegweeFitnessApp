git add .
git commit -m "fix"
git reset -- src\main\resources\ingerdient_images\*
git push heroku master
heroku open