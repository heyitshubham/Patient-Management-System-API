# Patient Management System API in Spring Boot

This project is a backend API implementation for a patient management system. The goal of the project is to provide APIs for doctors to register their patients through a mobile or web portal. The backend APIs were developed in Spring Boot.

## Features

The following are the features of the system:

- Adding a doctor
- Getting a list of doctors
- Getting a list of doctors by city
- Getting a list of doctors by city and speciality
- Updating a doctor's phone number
- Deleting a doctor by ID
- Adding a patient and their symptom
- Suggesting a doctor based on a patient's symptom

## Doctor's Entity

In our database, we store the following details of doctors:

- Name
- City
- Email
- Phone number
- Speciality

City can have 3 values only: Delhi, Noida, Faridabad. Speciality can have 4 values: Orthopedic, Gynecology, Dermatology, ENT specialist.

A doctor can be added or removed from the platform.

## Patient's Entity

In our database, we store the following details of patients:

- Name
- City
- Email
- Phone number
- Symptom

City can have any value. Symptom can have the following values only:

- Arthritis, Backpain, Tissue injuries (comes under Orthopedic speciality)
- Dysmenorrhea (comes under Gynecology speciality)
- Skin infection, skin burn (comes under Dermatology speciality)
- Ear pain (comes under ENT speciality)

A patient can be added or removed from the platform.

The following fields have the mentioned validations at the backend:

1. Name (should be at least 3 characters)
2. City (should be at max 20 characters)
3. Email (should be a valid email address)
4. Phone number (should be at least 10 numbers)

## Suggesting Doctors

There is another API that will accept a patient ID and suggest doctors based on the patient's location and symptom.

For example, if the patient has arthritis as a symptom, then all doctors of that location who are orthopedic will be sent as the response since arthritis comes under the orthopedic specialty.

Important Note: The suggesting doctor API is the evaluating API where the logic needs to be working. The suggested doctor in the API should be based on the symptom of the patient that links to the doctor's speciality. If a patient has eye pain, then only an ENT specialist doctor should be suggested.

### Edge Cases

1. If there aren't any doctors in that location (i.e., outside Delhi, Noida, Faridabad), the response should be "We are still waiting to expand to your location."
2. If there aren't any doctors for that symptom in that location, the response should be "There aren't any doctors present at your location for your symptom."
