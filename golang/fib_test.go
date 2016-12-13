package fib

import (
	"math/big"
	"testing"
)

var fibs = []struct {
	n        int64 // input
	expected int64 // expected result
}{
	{1, 1},
	{2, 1},
	{3, 2},
	{4, 3},
	{5, 5},
	{6, 8},
	{7, 13},
}

// avoiding compiler optimisations
var res *big.Int

func TestF(t *testing.T) {
	impl := make([]Fib, 3)
	impl[0] = new(IterFibMem)
	impl[1] = new(RecFibMem)
	impl[2] = new(IterativeFib)
	// impl[3] = new(GoldenRoundFib)

	for _, f := range impl {
		f.Init(100)

		for _, tt := range fibs {
			actual := f.NthFib(tt.n)
			if actual.Int64() != tt.expected {
				t.Errorf("%s.NthFib(%d): expected %d, actual %d", f, tt.n, tt.expected, actual)
			}
		}

	}
}

func benchmarkF(n int64, f Fib, b *testing.B) {
	f.Init(n)

	var r *big.Int
	for i := int64(10); i < n; i++ {
		r = f.NthFib(int64(i))
	}

	res = r
}

// func BenchmarkRecFibMem10000(b *testing.B) {
// 	f := new(RecFibMem)
// 	benchmarkF(10000, f, b)
// }

// func BenchmarkRecFibMem20000(b *testing.B) {
// 	f := new(RecFibMem)
// 	benchmarkF(20000, f, b)
// }

// func BenchmarkRecFibMem100000(b *testing.B) {
// 	f := new(RecFibMem)
// 	benchmarkF(100000, f, b)
// }

// func BenchmarkRecFibMem200000(b *testing.B) {
// 	f := new(RecFibMem)
// 	benchmarkF(200000, f, b)
// }

// func BenchmarkRecFibMem300000(b *testing.B) {
// 	f := new(RecFibMem)
// 	benchmarkF(300000, f, b)
// }

// func BenchmarkIterFibMem10000(b *testing.B) {
// 	f := new(IterFibMem)
// 	benchmarkF(10000, f, b)
// }

// func BenchmarkIterFibMem20000(b *testing.B) {
// 	f := new(IterFibMem)
// 	benchmarkF(20000, f, b)
// }

// func BenchmarkIterFibMem100000(b *testing.B) {
// 	f := new(IterFibMem)
// 	benchmarkF(100000, f, b)
// }
// func BenchmarkIterFibMem200000(b *testing.B) {
// 	f := new(IterFibMem)
// 	benchmarkF(200000, f, b)
// }

// func BenchmarkIterFibMem300000(b *testing.B) {
// 	f := new(IterFibMem)
// 	benchmarkF(300000, f, b)
// }

// no
// func BenchmarkGoldenRoundFib10000(b *testing.B) {
// 	f := new(GoldenRoundFib)
// 	benchmarkF(10000, f, b)
// }

// func BenchmarkGoldenRoundFib20000(b *testing.B) {
// 	f := new(GoldenRoundFib)
// 	benchmarkF(20000, f, b)
// }

// func BenchmarkGoldenRoundFib100000(b *testing.B) {
// 	f := new(GoldenRoundFib)
// 	benchmarkF(100000, f, b)
// }

func BenchmarkMatrixFib10000(b *testing.B) {
	f := new(IterativeFib)
	f.Init(10000)
	res = f.NthFib(10000)
}

func BenchmarkMatrixFib100000(b *testing.B) {
	f := new(IterativeFib)
	f.Init(100000)
	res = f.NthFib(100000)
}

func BenchmarkMatrixFib200000(b *testing.B) {
	f := new(IterativeFib)
	f.Init(200000)
	res = f.NthFib(200000)
}

func BenchmarkMatrixFib300000(b *testing.B) {
	f := new(IterativeFib)
	f.Init(300000)
	res = f.NthFib(300000)
}

// func BenchmarkMatrixFib20000(b *testing.B) {
// 	f := new(MatrixFib)
// 	benchmarkF(20000, f, b)
// }

// func BenchmarkMatrixFib100000(b *testing.B) {
// 	f := new(MatrixFib)
// 	benchmarkF(100000, f, b)
// }
// func BenchmarkMatrixFib200000(b *testing.B) {
// 	f := new(MatrixFib)
// 	benchmarkF(200000, f, b)
// }

// func BenchmarkMatrixFib300000(b *testing.B) {
// 	f := new(MatrixFib)
// 	benchmarkF(300000, f, b)
// }
