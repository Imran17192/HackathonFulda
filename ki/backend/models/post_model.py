from datetime import date
from typing import Optional, List

class PostData:
    def __init__(self, title: str, content: str, category: Optional[str] = None,
                 date_of_achievement: Optional[date] = None, tags: Optional[List[str]] = None):
        self.title = title
        self.content = content
        self.category = category
        self.date_of_achievement = date_of_achievement
        self.tags = tags or []
        self.summary = self.generate_summary()

    def generate_summary(self) -> str:
        # Generates a summary based on the content (first 50 characters)
        return self.content[:50] + "..." if len(self.content) > 50 else self.content
