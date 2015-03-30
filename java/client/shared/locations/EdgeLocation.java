package shared.locations;

/**
 * Represents the location of an edge on a hex map
 */
public class EdgeLocation
{
	
	private HexLocation hexLoc;
	private EdgeDirection dir;
	
	public EdgeLocation(HexLocation hexLoc, EdgeDirection dir)
	{
		setHexLoc(hexLoc);
		setDir(dir);
	}
	
	public HexLocation getHexLoc()
	{
		return hexLoc;
	}
	
	private void setHexLoc(HexLocation hexLoc)
	{
		if(hexLoc == null)
		{
			throw new IllegalArgumentException("hexLoc cannot be null");
		}
		this.hexLoc = hexLoc;
	}
	
	public EdgeDirection getDir()
	{
		return dir;
	}
	
	private void setDir(EdgeDirection dir)
	{
		this.dir = dir;
	}
	
	@Override
	public String toString()
	{
		return "EdgeLocation [hexLoc=" + hexLoc + ", dir=" + dir + "]";
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dir == null) ? 0 : dir.hashCode());
		result = prime * result + ((hexLoc == null) ? 0 : hexLoc.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		EdgeLocation other = (EdgeLocation)obj;
		if(dir != other.dir)
			return false;
		if(hexLoc == null)
		{
			if(other.hexLoc != null)
				return false;
		}
		else if(!hexLoc.equals(other.hexLoc))
			return false;
		return true;
	}
	
	/**
	 * Returns a canonical (i.e., unique) value for this edge location. Since
	 * each edge has two different locations on a map, this method converts a
	 * hex location to a single canonical form. This is useful for using hex
	 * locations as map keys.
	 * 
	 * @return Normalized hex location
	 */
	public EdgeLocation getNormalizedLocation()
	{
		
		// Return an EdgeLocation that has direction NW, N, or NE
		
		switch (dir)
		{
			case NorthWest:
			case North:
			case NorthEast:
				return this;
			case SouthWest:
			case South:
			case SouthEast:
				return new EdgeLocation(hexLoc.getNeighborLoc(dir),
										dir.getOppositeDirection());
			default:
				assert false;
				return null;
		}
	}

    public EdgeLocation getClockwiseEdge() {

        switch (dir)
        {
            case NorthWest:
                return new EdgeLocation(hexLoc, EdgeDirection.North);
            case North:
                return new EdgeLocation(hexLoc, EdgeDirection.NorthEast);
            case NorthEast:
                return new EdgeLocation(hexLoc, EdgeDirection.SouthEast);
            case SouthEast:
                return new EdgeLocation(hexLoc, EdgeDirection.South);
            case South:
                return new EdgeLocation(hexLoc, EdgeDirection.SouthWest);
            case SouthWest:
                return new EdgeLocation(hexLoc, EdgeDirection.NorthWest);
            default:
                assert false;
                return null;
        }
    }

    public EdgeLocation getCounterClockwiseEdge() {

        switch (dir)
        {
            case NorthEast:
                return new EdgeLocation(hexLoc, EdgeDirection.North);
            case SouthEast:
                return new EdgeLocation(hexLoc, EdgeDirection.NorthEast);
            case South:
                return new EdgeLocation(hexLoc, EdgeDirection.SouthEast);
            case SouthWest:
                return new EdgeLocation(hexLoc, EdgeDirection.South);
            case NorthWest:
                return new EdgeLocation(hexLoc, EdgeDirection.SouthWest);
            case North:
                return new EdgeLocation(hexLoc, EdgeDirection.NorthWest);
            default:
                assert false;
                return null;
        }
    }

    public VertexLocation getClockwiseVertex() {

        switch (dir)
        {
            case NorthWest:
                return new VertexLocation(hexLoc, VertexDirection.NorthWest);
            case North:
                return new VertexLocation(hexLoc, VertexDirection.NorthEast);
            case NorthEast:
                return new VertexLocation(hexLoc, VertexDirection.East);
            case SouthEast:
                return new VertexLocation(hexLoc, VertexDirection.SouthEast);
            case South:
                return new VertexLocation(hexLoc, VertexDirection.SouthWest);
            case SouthWest:
                return new VertexLocation(hexLoc, VertexDirection.West);
            default:
                assert false;
                return null;
        }
    }

    public VertexLocation getCounterClockwiseVertex() {

        switch (dir)
        {
            case North:
                return new VertexLocation(hexLoc, VertexDirection.NorthWest);
            case NorthEast:
                return new VertexLocation(hexLoc, VertexDirection.NorthEast);
            case SouthEast:
                return new VertexLocation(hexLoc, VertexDirection.East);
            case South:
                return new VertexLocation(hexLoc, VertexDirection.SouthEast);
            case SouthWest:
                return new VertexLocation(hexLoc, VertexDirection.SouthWest);
            case NorthWest:
                return new VertexLocation(hexLoc, VertexDirection.West);
            default:
                assert false;
                return null;
        }
    }

}

