Stream.of(
Block.box(0, 1, 0, 3, 15, 16),
Block.box(13, 1, 0, 16, 15, 16),
Block.box(3, 1, 14, 13, 15, 16),
Block.box(1, 0, 1, 15, 1, 15),
Block.box(1, 15, 1, 15, 16, 15),
Block.box(3, 1, 1, 13, 4, 14),
Block.box(3, 12, 1, 13, 15, 14),
Block.box(3, 4, 2, 13, 12, 3)
).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();