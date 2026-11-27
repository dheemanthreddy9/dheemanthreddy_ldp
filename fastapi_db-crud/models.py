
from sqlalchemy import Column, Integer, String, Float
from database import Base


class Fertilizer(Base):
    __tablename__ = "fertilizers"

    id = Column(Integer, primary_key=True, index=True)
    name = Column(String, nullable=False)
    brand = Column(String, nullable=False)
    fertilizer_type = Column(String, nullable=False)
    price = Column(Float, nullable=False)
