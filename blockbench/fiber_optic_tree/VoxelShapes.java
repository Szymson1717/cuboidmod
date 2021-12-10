Stream.of(
Block.box(0, 0, 0, 16, 1, 16),
Block.box(1, 1, 1, 15, 2, 15),
Block.box(7, 2, 7, 9, 15, 9),
Block.box(7.5, 15, 7.5, 8.5, 16, 8.5),
Block.box(6, 12, 6, 10, 13, 10),
Block.box(5, 9.5, 5, 11, 10.5, 11),
Block.box(4, 7, 4, 12, 8, 12),
Block.box(3, 4, 3, 13, 5, 13),
Block.box(0, 1, 0, 16, 2, 1),
Block.box(0, 1, 1, 1, 2, 15),
Block.box(0, 1, 15, 16, 2, 16),
Block.box(15, 1, 1, 16, 2, 15)
).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();