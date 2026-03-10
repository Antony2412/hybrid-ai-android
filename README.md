# Hybrid AI Android Assistant

A **Hybrid AI-powered Android Assistant** built using **Kotlin, Jetpack Compose, and FastAPI** that intelligently routes user queries between local processing and a cloud-based LLM.

The system demonstrates a modern **agent-style AI architecture** where a decision engine determines whether a query should be handled locally or sent to a cloud backend.

---

# Architecture

The application follows a **hybrid AI architecture** where a decision engine determines whether a query should be processed locally or sent to the cloud.

```
User Query
    ↓
Android App (Jetpack Compose)
    ↓
Intent Classifier
    ↓
Decision Engine
    ├── Simple Queries → Local Processing
    └── Complex Queries → FastAPI Backend → LLM
```

---

# Features

* Modern Android UI built with **Jetpack Compose**
* **Hybrid AI routing system**
* Intent classification for query understanding
* Decision engine to route queries locally or to the cloud
* **FastAPI backend** for LLM communication
* Markdown rendering for AI responses
* AI typing indicator
* Clean chat interface with message bubbles
* Retrofit networking layer
* Secure API key management using environment variables

---

# Tech Stack

## Android Application

* Kotlin
* Jetpack Compose
* Retrofit
* Coroutines
* MVVM Architecture

## Backend

* Python
* FastAPI
* OpenAI API
* Uvicorn

---

# Project Structure

```
hybrid-ai-android-assistant
│
├── HybridAIAssistant
│   ├── app
│   │   ├── data
│   │   │   └── remote
│   │   ├── decision
│   │   ├── intent
│   │   ├── ui
│   │   │   └── chat
│   │   └── MainActivity.kt
│   │
│   └── build.gradle
│
├── backend
│   ├── main.py
│   └── requirements.txt
│
└── README.md
```

---

# Setup Instructions

## Backend Setup

Navigate to the backend directory:

```
cd backend
```

Install dependencies:

```
pip install -r requirements.txt
```

Create an environment variable for the API key:

```
OPENAI_API_KEY=your_api_key
```

Run the FastAPI server:

```
uvicorn main:app --reload
```

The backend will start at:

```
http://localhost:8000
```

You can view the API documentation at:

```
http://localhost:8000/docs
```

---

## Android App Setup

Open the project in **Android Studio**.

Run the application on an emulator or Android device.

The Android application communicates with the backend using:

```
http://10.0.2.2:8000
```

This allows the Android emulator to access the local backend server.

---

# Screenshots

Add screenshots of the chat interface here.

Example:

```
screenshots/chat_ui.png
```

---

# Future Improvements

* On-device LLM inference
* Streaming responses from backend
* Voice-based AI interaction
* RAG-based knowledge retrieval
* Local AI inference using quantized models

---

# Author

**Antony Joy**

Associate Software Developer
Android Developer | AI Enthusiast

---

# License

This project is for educational and experimental purposes.
