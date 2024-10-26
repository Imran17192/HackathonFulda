from pydantic import BaseModel

class PostData(BaseModel):
    title: str
    content: str

    class Config:
        schema_extra = {
            "example": {
                "title": "My Achievement",
                "content": "I completed a project in the healthcare sector using machine learning."
            }
        }
