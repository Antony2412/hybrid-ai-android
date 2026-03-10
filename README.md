# Hybrid AI Android Assistant

A hybrid AI assistant built using **Kotlin, Jetpack Compose, and FastAPI** that intelligently routes queries between on-device logic and a cloud-based LLM.

## Architecture

The application follows a **hybrid AI architecture** where a decision engine determines whether a query should be processed locally or sent to the cloud.

User Query
↓
Android App (Jetpack Compose)
↓
Intent Classifier
↓
Decision Engine
├── Simple Queries → Local Processing
└── Complex Queries → FastAPI Backend → LLM

## Features

* Modern Android UI built with **Jetpack Compose**
* Hybrid AI routing using a **decision engine**
* FastAPI backend for LLM interaction
* Markdown rendering for AI responses
* Typing indicator for AI responses
* Clean chat UI with message bubbles
* Retrofit-based networking layer
* Secure API key management using environment variables

## Tech Stack

### Mobile App

* Kotlin
* Jetpack Compose
* Retrofit
* Coroutines
* MVVM Architecture

### Backend

* FastAPI
* Python
* OpenAI API

## Project Structure

```
hybrid-ai-android-assistant
│
├── HybridAIAssistant
│   └── Android App (Jetpack Compose)
│
├── backend
│   ├── main.py
│   └── API integration
│
└── README.md
```

## Setup

### Backend

```
cd backend
pip install -r requirements.txt
uvicorn main:app --reload
```

Backend runs on:

```
http://localhost:8000
```

### Android App

Open the project in **Android Studio** and run the application on an emulator or device.

The Android app communicates with the backend using:

```
http://10.0.2.2:8000
```

## Future Improvements

* On-device LLM inference
* Streaming responses from the backend
* Voice-based interaction
* RAG-based knowledge retrieval

## Author

Antony Joy
Associate Software Developer | Android Developer
