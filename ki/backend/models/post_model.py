from pydantic import BaseModel, Field, root_validator
from typing import Optional, List
from datetime import date

class PostData(BaseModel):
    title: str = Field(..., min_length=5, max_length=100, description="The title summarizing the achievement or activity")
    content: str = Field(..., min_length=20, description="Detailed description of the achievement or activity")
    category: Optional[str] = Field(None, description="The category of the achievement, e.g., 'healthcare', 'education'")
    date_of_achievement: Optional[date] = Field(None, description="The date the achievement was completed")
    tags: Optional[List[str]] = Field(default=[], description="List of keywords or tags related to the post")
    summary: Optional[str] = Field(None, description="Auto-generated summary of the content")

    # Root validator to generate summary based on content
    @root_validator(pre=True)
    def generate_summary(cls, values):
        content = values.get("content", "")
        values["summary"] = content[:50] + "..." if len(content) > 50 else content
        return values

    # Configuration for schema example and JSON encoding
    class Config:
        schema_extra = {
            "example": {
                "title": "Machine Learning in Healthcare",
                "content": "Successfully implemented a machine learning model to improve diagnostic accuracy in healthcare settings.",
                "category": "healthcare",
                "date_of_achievement": "2023-05-10",
                "tags": ["machine learning", "healthcare", "AI"],
                "summary": "Successfully implemented a machine learning model to..."
            }
        }
        json_encoders = {
            date: lambda v: v.strftime('%Y-%m-%d')  # Ensures date is serialized as 'YYYY-MM-DD'
        }
