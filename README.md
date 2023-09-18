# University schedule service

Project for university schedule

## Functional requirments

1. User authorization and authentication
2. Role based access control(teacher, admin)

### What user can

1. Unauthorized user
    * Viewing the timetable of all groups and teachers
2. Teacher
    * All as unauthorized user
    * Add comments to his classes
    * Edit his classes' attributes(classroom, class type, start and end time, week_number, day of week)
3. Admin
    * All as unauthorized user and teacher
    * Create and delete users
    * Assign roles
    * Create and delete classrooms
    * Create and delete groups
    * Create and delete faculties
    * Create and delete subjects
    * Create and delete teachers
    * Create and delete classes
 
## Database diagram

![](https://gist.githubusercontent.com/KiryaHandsome/6e774d564c034c506844f6b4391d4328/raw/4f078ac75e799c906476c4b6c947fdbff246e343/db_diagram.png)
